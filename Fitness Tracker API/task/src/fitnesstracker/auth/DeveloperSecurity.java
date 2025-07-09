package fitnesstracker.auth;

import fitnesstracker.model.entity.Developer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DeveloperSecurity implements UserDetails {

    private final Developer developer;

    public DeveloperSecurity(Developer developer) {
        this.developer = developer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = developer.getAuthority() != null ? developer.getAuthority() : "ROLE_USER";
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return developer.getPassword();
    }

    @Override
    public String getUsername() {
        return developer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
