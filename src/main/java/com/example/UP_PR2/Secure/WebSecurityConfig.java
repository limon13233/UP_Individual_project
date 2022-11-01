package com.example.UP_PR2.Secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/login","/registration","/filter").permitAll().
                antMatchers("/create-order/**","/Delete/order/**").hasAnyAuthority("USER").
                antMatchers("/Edit/**","/ADD/**","/Delete/**").hasAnyAuthority("EMPLOYEE").
                antMatchers("/employee/admin/**").hasAnyAuthority("ADMIN").
                anyRequest().authenticated().
                and().formLogin().loginPage("/login").permitAll().
                and().logout().permitAll().
                and().csrf().disable().cors().disable();
  }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                    passwordEncoder(passwordEncoder).
                usersByUsernameQuery("SELECT username, password, active FROM user WHERE username = ?").
                authoritiesByUsernameQuery("SELECT u.username, ur.roles FROM user u INNER JOIN user_roles ur on u.id = ur.id_user WHERE username = ?");
    }
    @Lazy
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
