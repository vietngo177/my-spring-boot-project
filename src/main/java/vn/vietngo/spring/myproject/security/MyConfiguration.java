package vn.vietngo.spring.myproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MyConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT tendangnhap, matkhau, active from accounts where tendangnhap=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select a.tendangnhap, r.role from accounts a, roles r, accounts_roles x where a.id=x.account_id and r.id=x.role_id and a.tendangnhap=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer->configurer
                        .requestMatchers("/book").permitAll()
                        .requestMatchers("/author/**","book/**","genre/**","/account/list","/account/add","/account/authorize","account/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/account/**","/changepassword/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().permitAll()
        ).formLogin(
                form->form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index",true)
//                        .failureUrl("/login?error=true")
        ).logout(
                logout->logout.permitAll()
                            .deleteCookies("remember-me")
        ).rememberMe(
                remember->remember.tokenValiditySeconds(604800)
        );


        return http.build();
    }
}
