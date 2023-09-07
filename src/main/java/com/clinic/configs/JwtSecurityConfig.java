/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.configs;

import com.clinic.filters.CustomAccessDeniedHandler;
import com.clinic.filters.JwtAuthenticationTokenFilter;
import com.clinic.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.clinic.controllers",
    "com.clinic.repository",
    "com.clinic.service",
    "com.clinic.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/departments/").permitAll();

        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/bill").access("hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/medicine-bill").access("hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/categories").access("hasRole('ROLE_DOCTOR')")
                .antMatchers(HttpMethod.GET, "/api/doctors").access("hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.GET, "/api/hours").access("hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.GET, "/api/medical-report").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/report-details/**").access("hasRole('ROLE_DOCTOR')")
                .antMatchers(HttpMethod.POST, "/api/medical-report/").access("hasRole('ROLE_DOCTOR')")

                .antMatchers(HttpMethod.GET, "/api/patient").access("hasRole('ROLE_DOCTOR')")

                
                .antMatchers(HttpMethod.PATCH, "/api/medical-report").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/medicines").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/medicine-unit").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.POST, "/api/register-patient").access("hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.GET, "/api/regulation").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/new-regulation").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/schedule-details")
                .access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.GET, "/api/schedule-details/**")
                .access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_PATIENT')")
                
                .antMatchers(HttpMethod.GET, "/api/schedule-details?patientId=")
                .access("hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.POST, "/api/schedule-details/")
                .access("hasRole('ROLE_PATIENT') or hasRole('ROLE_NURSE')")
                
                .antMatchers(HttpMethod.GET, "/api/schedule-details/")
                .access("hasRole('ROLE_NURSE')")
                
                .antMatchers(HttpMethod.GET, "/api/schedule-details/count").access("hasRole('ROLE_PATIENT') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.PATCH, "/api/schedule-details/cancel/**").access("hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.PATCH, "/api/schedule-details/").access("hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/new-regulation").access("hasRole('ROLE_PATIENT') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/medicines").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/medicine-bill").access("hasRole('ROLE_NURSE')")
                .antMatchers(HttpMethod.GET, "/api/bill").access("hasRole('ROLE_NURSE')")


                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT')").and()

                

                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }

//    @Override
//protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().ignoringAntMatchers("/api/**")
//        .and()
//        .authorizeRequests()
//            .antMatchers("/api/login/").permitAll()
//            .antMatchers("/api/users/").permitAll()
//            .antMatchers("/api/departments/").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/bill").access("hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/medicine-bill").access("hasRole('ROLE_NURSE')")
//            // Add more antMatchers as needed
//        .anyRequest().authenticated()
//        .and()
//        .exceptionHandling(e -> e
//            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//        .oauth2Login()
//        .and()
//        .antMatcher("/api/**")
//        .httpBasic().authenticationEntryPoint(restServicesEntryPoint())
//        .and()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/api/medicine-bill").access("hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/categories").access("hasRole('ROLE_DOCTOR')")
//            .antMatchers(HttpMethod.GET, "/api/doctors").access("hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.GET, "/api/hours").access("hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.GET, "/api/medical-report").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/bill").access("hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/medicine-bill").access("hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/categories").access("hasRole('ROLE_DOCTOR')")
//            .antMatchers(HttpMethod.GET, "/api/doctors").access("hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.GET, "/api/hours").access("hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.GET, "/api/medical-report").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.PATCH, "/api/medical-report").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/medicines").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/medicine-unit").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.POST, "/api/register-patient").access("hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.GET, "/api/regulation").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/new-regulation").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/schedule-details")
//                .access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.GET, "/api/schedule-details/**")
//                .access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.POST, "/api/schedule-details/")
//                .access("hasRole('ROLE_PATIENT') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.PATCH, "/api/schedule-details/").access("hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.GET, "/api/medicines").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
//            .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT')")
//            .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT')")
//        .and()
//        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
//}
}
