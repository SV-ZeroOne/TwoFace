package za.co.entelect.bootcamp.twoface.squareeyes.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AuthenticationService;

/**
 * Created by mpho.mahase on 2017/02/01.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationService authenticationService;


    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/login").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
        http
                .authorizeRequests()
                .antMatchers( "/checkout").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/loginError")
                .usernameParameter("user")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/logout");

//        http
//                .authorizeRequests()
//                .anyRequest().anonymous()
//                .and()
//                .formLogin();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(authenticationService);
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN");
    }

    /*@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetails);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }*/

}