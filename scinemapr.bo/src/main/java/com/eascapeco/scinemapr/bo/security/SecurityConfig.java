package com.eascapeco.scinemapr.bo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eascapeco.scinemapr.bo.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider jwttokenProvider;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	/*
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
					.antMatchers("/loginForm", "/api/admin/login", "/favicon.ico", "/api/menus", "/api/menus/*", "/api/roles", "/error").permitAll()
					.anyRequest().hasRole("USER")
			.and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
			.and()
				.addFilterBefore(new JWTAuthenticationFilter(jwttokenProvider), UsernamePasswordAuthenticationFilter.class);
		//super.configure(http);
	}
	
	@Override
	public void init(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**");
		super.init(web);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
