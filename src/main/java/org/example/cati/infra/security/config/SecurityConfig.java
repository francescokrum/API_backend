package org.example.cati.infra.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crsf->crsf.disable())
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/cliente/cadastrarCliente").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cliente").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cliente/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cliente/editarCliente").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/cliente/{id}").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST,"/desenvolvedor/cadastrarDesenvolvedor").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/desenvolvedor/{id}").hasAnyRole("ADMIN", "DEV")
                        .requestMatchers(HttpMethod.GET,"/desenvolvedor").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/desenvolvedor/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/desenvolvedor/editarDesenvolvedor").hasRole("DEV")
                        .requestMatchers(HttpMethod.POST, "/unidadeDeNegocio/cadastrarUnidadeDeNegocio").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/unidadeDeNegocio").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/unidadeDeNegocio/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/unidadeDeNegocio/editarUnidadeDeNegocio").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/unidadeDeNegocio/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200/");
        config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
        config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



}
