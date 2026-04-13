//package com.springboot.rest_api_jparepository.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DemoSecurityConfig {
//
////    /**
////     * define inline users for rolebase access
////     * @return
////     */
////    @Bean
////    public InMemoryUserDetailsManager userDetailsManager(){
////
////        UserDetails harsh = User.builder()
////                .username("john")
////                .password("{noop}test123")
////                .roles("EMPLOYEE")
////                .build();
////
////        UserDetails john = User.builder()
////                .username("john")
////                .password("{noop}test123")
////                .roles("EMPLOYEE")
////                .build();
////
////        UserDetails mary = User.builder()
////                .username("mary")
////                .password("{noop}test123")
////                .roles("EMPLOYEE", "MANAGER")
////                .build();
////
////        UserDetails susan = User.builder()
////                .username("susan")
////                .password("{noop}test123")
////                .roles("EMPLOYEE", "MANAGER", "ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(john, mary, susan);
////    }
//
//    /**
//     * setting rolebase access to user on api calls via user role
//     * @param http
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
//        );
//
//        //http basic authentication
//        http.httpBasic(Customizer.withDefaults());
//
//        //disable csrf
//        http.csrf(csrf->csrf.disable());
//        return http.build();
//    }
//
////    /**
////     * default implementation of retrive user and role using inbuilt methods
////     * @param dataSource
////     * @return
////     */
////    @Bean
////    public UserDetailsManager userDetailsManager (DataSource dataSource){
////        return new JdbcUserDetailsManager(dataSource);
////    }
//
//
//    /**
//     * costom table qury for select user and role
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public UserDetailsManager userDetailsManager (DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager.setUsersByUsernameQuery(
//                "select user_id, pw, active from members where user_id=?"
//        );
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//                "select user_id, role from roles where user_id=?"
//        );
//
//        return jdbcUserDetailsManager;
//    }
//
//}
//
