package fitnesstracker.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final ApikeyAuthenticationProvider provider;

    public SecurityConfig(ApikeyAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authManager,
                                                   UserDetailsService userDetailsService) throws Exception {
        ApikeyFilter apikeyFilter = new ApikeyFilter(authManager);

        return http
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable()) // For Postman
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // For the H2 console// for POST requests via Postman
                .sessionManagement(sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                )
                .addFilterBefore(
                        new ApikeyFilter(authManager),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/shutdown").permitAll() // for tests
                        .requestMatchers("/error").permitAll() // to prevent access errors for validation exceptions
                        .requestMatchers(HttpMethod.POST, "/api/developers/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/developers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/applications/register").authenticated()
                        .requestMatchers( "/api/tracker").authenticated()
                        .anyRequest().permitAll()
                )
                .authenticationProvider(provider)
                .userDetailsService(userDetailsService)
                .csrf(AbstractHttpConfigurer::disable)
                .build();

        /*
        return
        http
//                .httpBasic(Customizer.withDefaults())     // Default Basic auth config
                .csrf(csrf -> csrf.disable()) // For Postman
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // For the H2 console// for POST requests via Postman
                .sessionManagement(sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/shutdown").permitAll() // for tests
                        .requestMatchers("/error").permitAll() // to prevent access errors for validation exceptions
                        .requestMatchers(HttpMethod.POST, "/api/developers/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/developers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/applications/register").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/tracker").authenticated()
                        .anyRequest().permitAll()
                )
                .authenticationProvider(provider)
                .addFilterAfter(apikeyFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

         */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
