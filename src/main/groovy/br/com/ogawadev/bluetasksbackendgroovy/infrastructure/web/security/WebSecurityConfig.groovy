package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web.security

import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@Slf4j
class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                    .headers().frameOptions().disable()

        http.httpBasic()
            .and()
                .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        log.info("Security Setup... OK!")

    }

    @Override
    void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/login")
            .allowedOrigins("*")
            .allowedMethods("POST")
            .exposedHeaders(SecurityConstants.AUTHORIZATION_HEADER)

        log.info("CORS Setup... OK!")
    }
}
