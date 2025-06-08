package fitnesstracker.auth;

import fitnesstracker.entity.Application;
import fitnesstracker.repository.ApplicationRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApikeyAuthenticationProvider implements AuthenticationProvider {
    private final ApplicationRepository applicationRepository;

    public ApikeyAuthenticationProvider(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Transactional(noRollbackFor = BadCredentialsException.class)
    @Override
    public Authentication authenticate(Authentication authentication) {
        String requestApikey = authentication.getCredentials().toString();

        Application application = applicationRepository
                .findByApikey(requestApikey)
                .orElseThrow(() -> new BadCredentialsException("Invalid access apikey"));

        authentication.setAuthenticated(true);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApikeyAuthentication.class.equals(authentication);
    }
}
