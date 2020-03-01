package com.yeongjae.damoim.global.security.config;

import com.yeongjae.damoim.global.filter.AccessDeniedHandlerCustom;
import com.yeongjae.damoim.global.filter.AuthenticationEntryPointCustom;
import com.yeongjae.damoim.global.filter.JwtAuthenticationFilter;
import com.yeongjae.damoim.global.jwt.JwtService;
import com.yeongjae.damoim.global.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandlerCustom accessDeniedHandlerCustom;
    private final AuthenticationEntryPointCustom authenticationEntryPointCustom;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private static final String[] AUTH_ARR = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/webjars/**",
            "favicon.ico"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/damoim/sign/signIn").permitAll()
                        .antMatchers(HttpMethod.POST, "/damoim/sign").permitAll()
                        .anyRequest().hasRole("USER")
                .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandlerCustom)
                .and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPointCustom)
                .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(AUTH_ARR)
                .antMatchers("/damoim/sign/pw")
                .antMatchers("/damoim/sign/email")
                .antMatchers("/damoim/sign/check/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
