package com.example.demo;

import com.example.demo.User.CustomOAuth2User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import java.util.Arrays;
import java.util.Map;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public SecurityConfig(UserDetailsService userDetailsService, ClientRegistrationRepository clientRegistrationRepository) {
        this.userDetailsService = userDetailsService;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**", "/login").permitAll()
                .antMatchers("/secure/**").hasIpAddress("127.0.0.1")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint().oidcUserService(this::oidcUserService)
                .and()
                .and()
                .csrf().requireCsrfProtectionMatcher(csrfRequestMatcher())
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    private OidcUser oidcUserService(OidcUserRequest oidcUserRequest) {
        OidcUserService oidcUserService = new OidcUserService();
        return oidcUserService.loadUser(oidcUserRequest);
    }

    private OAuth2User ssoUser(OAuth2AuthenticationToken token) {
        // Assuming CustomOAuth2User constructor accepts OAuth2User as an argument
        OAuth2User oAuth2User = token.getPrincipal();
        return new CustomOAuth2User((Map<String, Object>) oAuth2User);
    }
    private RequestMatcher csrfRequestMatcher() {
        RequestMatcher unprotectedMethods = new RequestMatcher() {
            private final OrRequestMatcher requestMatchers = new OrRequestMatcher(
                    Arrays.asList(
                            new AntPathRequestMatcher("/public/**"),
                            new AntPathRequestMatcher("/login"),
                            new AntPathRequestMatcher("/secure/**"),
                            // Add more patterns as needed
                            new AntPathRequestMatcher("/oauth2/**") // Example: Protect OAuth2 endpoints
                    )
            );

            @Override
            public boolean matches(javax.servlet.http.HttpServletRequest request) {
                return !requestMatchers.matches(request);
            }
        };
        return unprotectedMethods;
    }
}
