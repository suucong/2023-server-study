package com.sujin.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import com.sujin.book.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들 활성화
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()  //
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService).and().and().build();
    }
}
