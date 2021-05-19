package org.example.Config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/account/**").authenticated()
                .antMatchers("/basket/**").authenticated()
                .antMatchers("/seller_account/**").hasRole("seller")
                .antMatchers("/admin_account/**").hasRole("admin")

                .and()
                .logout().logoutSuccessUrl("/")

                .and()
                .formLogin().loginPage("/start_session");

        http.csrf().disable();



    }
}
