package fpt.swp.WorkSpace.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(customizer -> customizer.disable())
                        .cors(Customizer.withDefaults())
                        .authorizeHttpRequests(configure -> configure
                        .requestMatchers( "/", "/api/auth/**" ).permitAll()
                                .requestMatchers("/customer/**").hasRole("USER")
                                .requestMatchers(("/staff/**")).hasRole("STAFF")
                                .requestMatchers(("/manager/**")).hasRole("MANAGER")
                        .anyRequest().authenticated())
                        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(applicationConfiguration.authenticationProvider())
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) ;

        return http.build();
    }



}
