package com.seafta.auth.config;

import com.seafta.auth.filter.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
////        http.authorizeRequests().anyRequest().permitAll();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers("/api/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("USER");
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/save/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin();
//        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
//        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/offers/**", "/accounts/**", "/files/**").hasAnyAuthority("USER", "COMPANY")
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
}
