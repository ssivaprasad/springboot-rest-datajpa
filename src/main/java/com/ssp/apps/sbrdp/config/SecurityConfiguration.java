package com.ssp.apps.sbrdp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ssp.apps.sbrdp.service.UserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * auth.inMemoryAuthentication().withUser("siva").password("siva").roles("USER").and()
         * .withUser("prasad").password("prasad").roles("ADMIN");
         */

        /*
         * auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
         * .withUser("siva").password("siva").roles("USER").and()
         * .withUser("prasad").password("prasad").roles("ADMIN");
         */

        /*
         * auth.jdbcAuthentication().dataSource(dataSource)
         * .usersByUsernameQuery("select username,password,enabled from Users where username = ?")
         * .authoritiesByUsernameQuery("select username,authority from authorities where username = ?"
         * );
         */

        /* auth.jdbcAuthentication().dataSource(dataSource); */

        auth.userDetailsService(userDetailsService);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
