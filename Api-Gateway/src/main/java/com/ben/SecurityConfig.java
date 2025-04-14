package com.ben;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.DefaultSecurityFilterChain;


@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeExchange(exchange -> exchange
//                        //.pathMatchers("/api/user/create").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 ->
//                        oauth2.jwt(Customizer.withDefaults()))
//                .build();
//    }

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String jwtSetUri;

    @Bean
    public DefaultSecurityFilterChain security(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwtSetUri).build();
    }
}
