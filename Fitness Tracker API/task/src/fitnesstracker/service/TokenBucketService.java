package fitnesstracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBucketService {

    private static final Logger log = LoggerFactory.getLogger(TokenBucketService.class);
    private final Map<Long, String> tokenBuckets = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 1_000)
    public void generateTokens() {
        for (Map.Entry<Long, String> entry : tokenBuckets.entrySet()) {
            if (entry.getValue() == null) {
                String token = generateToken();
                entry.setValue(token);
                log.debug("Generated new token for app {}", entry.getKey());
            }
        }
    }

    public boolean consumeToken(Long appId) {
        String storedToken = tokenBuckets.get(appId);
        if (storedToken == null) {
            return false;
        }
        tokenBuckets.put(appId, null);
        return true;
    }

    public void registerApp(Long appId) {
        tokenBuckets.putIfAbsent(appId, generateToken());
    }

    private String generateToken() {
        byte[] array = KeyGenerators.secureRandom(16).generateKey(); // 128-bit
        return new BigInteger(1, array).toString(16);
    }
}

