package com.muta1.italomutao.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.muta1.italomutao.filters.CorsFilter;
import com.muta1.italomutao.security.jwt.JwtAuthenticationEntryPoint;
import com.muta1.italomutao.security.jwt.JwtRequestFilter;
import com.muta1.italomutao.security.service.SecurityService;
import com.muta1.italomutao.user.dto.UserDTO;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityService detailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	public CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// We don't need CSRF for this example
		http.addFilterBefore(corsFilter(), SessionManagementFilter.class).csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests()
				.antMatchers("/login", "/unauthenticated-route/**", "/v2/api-docs", "/configuration/ui",
						"/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**","/webjars")
				.permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(UserDTO.PASSWORD_ENCODER);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}