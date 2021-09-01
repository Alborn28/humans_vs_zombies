package se.experis.humans_vs_zombies.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Custom converter to show roles instead of scopes
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this.jwtGrantedAuthoritiesConverter());

        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/game/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/v1/game/{gameId}/chat").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/game/{gameId}/squad").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/game/{gameId}/squad/{squadId}/chat").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/game/{gameId}/kill/{killId}").permitAll()

                .antMatchers(HttpMethod.POST, "/api/v1/game/**").hasRole("Administrator")
                .antMatchers(HttpMethod.PUT, "/api/v1/game/**").hasRole("Administrator")
                .antMatchers(HttpMethod.DELETE, "/api/v1/game/**").hasRole("Administrator")

                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                // Using our converter
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }

    // Implementation of replacing authorities with our roles
    @Bean
    public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        // You can use setAuthoritiesClaimName method
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        return jwtGrantedAuthoritiesConverter;
    }

}
