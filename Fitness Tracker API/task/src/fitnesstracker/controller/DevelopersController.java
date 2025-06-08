package fitnesstracker.controller;

import fitnesstracker.DeveloperProfileDto;
import fitnesstracker.entity.Application;
import fitnesstracker.service.DeveloperService;
import fitnesstracker.entity.Developer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/developers")
public class DevelopersController {

    private final DeveloperService developerService;
    private final PasswordEncoder passwordEncoder;

    public DevelopersController(DeveloperService developerService, PasswordEncoder passwordEncoder){
        this.developerService = developerService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody Developer request){
        if(request == null || developerService.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Developer developer = new Developer();
        developer.setEmail(request.getEmail());
        developer.setPassword(passwordEncoder.encode(request.getPassword()));

        Developer savedDeveloper = developerService.save(developer);
        URI location = URI.create("/api/developers/" + savedDeveloper.getId());
        return  ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@AuthenticationPrincipal UserDetails details, @PathVariable(name = "id") long id){
        if (details == null) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }

        Optional<Developer> optDeveloper = developerService.findByEmail(details.getUsername());
        if(optDeveloper.isEmpty()){
            return ResponseEntity.status(401).build();
        }

        Developer currDeveloper = optDeveloper.get();
        if(currDeveloper.getId() != id){
            return ResponseEntity.status(403).build();
        }

        List<Application> applicationList = new ArrayList<>(currDeveloper.getApplications());
        Collections.reverse(applicationList);

        return ResponseEntity.ok(new DeveloperProfileDto(
                currDeveloper.getId(),
                currDeveloper.getEmail(),
                applicationList));
    }
}
