package fitnesstracker.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class ApikeyAuthentication implements Authentication {
    private final String apikey;
    private boolean authenticated = false;

    public ApikeyAuthentication(String apikey) { this.apikey = apikey; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("X-API-Key"));
    }

    @Override
    public Object getCredentials() { return apikey; }

    @Override
    public Object getDetails() { return null; }

    @Override
    public Object getPrincipal() { return "bearer access apikey"; }

    @Override
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() { return "bearer access apikey"; }
}
