package fitnesstracker.auth;

import fitnesstracker.model.entity.Developer;
import fitnesstracker.repository.DeveloperRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final DeveloperRepository developerRepository;

    public UserDetailsServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Developer developer = developerRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return new DeveloperSecurity(developer);
    }
}