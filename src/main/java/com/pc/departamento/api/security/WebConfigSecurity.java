package com.pc.departamento.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementUserDetailService implementUserDetailService;
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() 
		.authorizeRequests() 
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/login").permitAll() 
		.defaultSuccessUrl("/")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login") 
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementUserDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/libs/**", "/jasper/**");
	}

}