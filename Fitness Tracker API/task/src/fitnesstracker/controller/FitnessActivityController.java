package fitnesstracker.controller;

import fitnesstracker.entity.AppCategory;
import fitnesstracker.entity.Application;
import fitnesstracker.entity.FitnessActivity;
import fitnesstracker.service.ApplicationService;
import fitnesstracker.service.FitnessActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracker")
public class FitnessActivityController {
    private final FitnessActivityService activityService;
    private final ApplicationService applicationService;

    private Map<Long, String> tokenBuckets = new HashMap<>();

    public FitnessActivityController(FitnessActivityService activityService,
                                     ApplicationService applicationService) {
        this.activityService = activityService;
        this.applicationService = applicationService;
    }

    @PostMapping()
    public ResponseEntity<?> postFitnessActivity(Authentication authentication,
                                                 @RequestBody FitnessActivity activity) {
        String apiKey = authentication.getCredentials().toString();
        if (apiKey == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Application> optApp = applicationService.findByApikey(apiKey);
        if (optApp.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        Application currApp = optApp.get();
        if(currApp.getCategory().equals(AppCategory.basic)){
            if(!tokenIsValid(currApp.getId())){
                return ResponseEntity.status(429).build();
            }
        }

        activity.setApplication(currApp);
        activityService.save(activity);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<?> getAll(Authentication authentication) {
        String apiKey = authentication.getCredentials().toString();
        if (apiKey == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Application> optApp = applicationService.findByApikey(apiKey);
        if (optApp.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        Application currApp = optApp.get();
        if(currApp.getCategory().equals(AppCategory.basic)){
            if(!tokenIsValid(currApp.getId())){
                return ResponseEntity.status(429).build();
            }
        }

        return ResponseEntity.ok(activityService.getAll());
    }

    @Scheduled(fixedRate = 1_000)
    private void setTokenBuckets() {
        for (Map.Entry<Long, String> entry : tokenBuckets.entrySet()) {
            byte[] array = KeyGenerators.secureRandom(4).generateKey();
            String token = new BigInteger(1, array).toString(16);
            if (entry.getValue() == null) {
                entry.setValue(token);
            }
        }
    }

    private boolean tokenIsValid(Long appId){
        if (!tokenBuckets.containsKey(appId)){
            tokenBuckets.put(appId, "a1b2c3d4e5");
        }

        if (tokenBuckets.get(appId) == null) {
            return false;
        }

        tokenBuckets.put(appId, null);
        return true;
    }
}
