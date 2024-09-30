package fpt.swp.WorkSpace.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfiguration {



    @Autowired
    private ApplicationConfiguration applicationConfiguration;


    @Autowired
    JWTAuthFilter jwtAuthFilter;




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                        .cors(c -> c.configurationSource(CorsConfig.corsConfigurationSource()))
                        .authorizeHttpRequests(configure -> configure
                        .requestMatchers("/api/auth/**" ).permitAll()
                                .requestMatchers("/api/customer/**").hasAuthority("CUSTOMER")
                                .requestMatchers("/api/staff/**").hasAnyAuthority("STAFF")
                                .requestMatchers("api/manager/**").hasAuthority("MANAGER")
                        .anyRequest().authenticated())
                        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(applicationConfiguration.authenticationProvider())
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) ;

        return http.build();


    }



}
