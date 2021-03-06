package nl.lotrac.bv.config;

import nl.lotrac.bv.filter.JwtRequestFilter;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/customers/**").hasRole(Role.CUSTOMER.toString())
                .antMatchers("/orders/create/**").hasRole(Role.CUSTOMER.toString())
                .antMatchers("/orders/inlog/**").hasRole(Role.CUSTOMER.toString())
                .antMatchers("/files/**").hasRole(Role.CUSTOMER.toString())


                .antMatchers("/users/**").hasAnyRole(Role.COMPANY_USER.toString(),Role.ADMIN.toString())
                .antMatchers("/orders/**").hasAnyRole(Role.COMPANY_USER.toString(),Role.ADMIN.toString())
                .antMatchers("/jobs/**").hasAnyRole(Role.COMPANY_USER.toString(),Role.ADMIN.toString())

                .antMatchers("/admin/**").hasAnyRole(Role.CUSTOMER.toString(),Role.COMPANY_USER.toString(),Role.ADMIN.toString())


                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}


