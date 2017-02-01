package za.co.entelect.bootcamp.twoface.squareeyes.web.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by mpho.mahase on 2017/02/01.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //Code recommended by Mat
        //        http
//                .authorizeRequests()
//                .mvcMatchers(http., "/HTTP")
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

}