package main.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static main.rest.security.ApplicationUserPermission.*;
import static main.rest.security.ApplicationUserRole.BAD_MAN;
import static main.rest.security.ApplicationUserRole.GOOD_MAN;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Security(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers(HttpMethod.GET, "/**").hasAnyRole(GOOD_MAN.name(), BAD_MAN.name())
                .antMatchers(HttpMethod.POST, "/**").hasAnyRole(GOOD_MAN.name())
                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority(USERS_WRITE.getPermission())
                .anyRequest().authenticated()
                .and().httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("Mandela")
                .password(passwordEncoder.encode("Nelson"))
                .authorities(GOOD_MAN.getGrantedAuthority())
                //.roles("GoodMan")
                .build();

        UserDetails user2 = User.builder()
                .username("Hitler")
                .password(passwordEncoder.encode("Adolf"))
                .authorities(BAD_MAN.getGrantedAuthority())
                //.roles("BadMan")
                .build();

        return new InMemoryUserDetailsManager(
                user1,
                user2
        );
    }


}
