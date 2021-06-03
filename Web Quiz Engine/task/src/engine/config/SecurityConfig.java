package engine.config;

import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    //-----------------------------------------------------------------------
    @Autowired
    private UserService userService;
    //-----------------------------------------------------------------------

    // Authorization : Role -> Access
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {/*     http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
       */
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/quizzes/**").hasRole("USER")
               // .antMatchers("/**").hasRole("ADMIN")
               .and()
                .csrf().disable()
                .headers()
                .frameOptions().disable();
              //  http.csrf().ignoringAntMatchers("/actuator/shutdown");
           //  http.csrf().ignoringAntMatchers("/shutdown");
      //  http.csrf().ignoringAntMatchers("/api/register");
    }
    //-----------------------------------------------------------------------
    // Authentication : User --> Roles
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception
    {/*
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("USER");
        */
      /*
         User.UserBuilder users= User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("kamil").password("123").roles("USER"))
                .withUser(users.username("mary").password("123").roles("USER","EMPLOYEE"))
                .withUser(users.username("susan").password("123").roles("ADMIN","USER"));
*/
        auth.authenticationProvider(authenticationProvider());
    }
    //-----------------------------------------------------------------------
    //beans
    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //-----------------------------------------------------------------------
    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt

        return auth;
    }
    //-----------------------------------------------------------------------
}
