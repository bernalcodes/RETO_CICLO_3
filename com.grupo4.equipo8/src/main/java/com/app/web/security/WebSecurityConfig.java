package com.app.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.web.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService((userDetailsService()));
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login",
						"/signup",
						"/logout",
						"/assets/**",
						"/css/**",
						"/js/**")
				.permitAll()
				.antMatchers("/users", "/users/**").hasAnyAuthority("ROOT", "ADMIN")
				.antMatchers("/history", "/history/**").hasAnyAuthority("ROOT", "ADMIN")
				.antMatchers("/vehicles").hasAnyAuthority("ROOT", "ADMIN", "EMPLEADO")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/index?success", true)
				.failureUrl("/login?error")
				.and()
				// .logout().logoutSuccessUrl("/login?logout").permitAll();
				.logout().logoutSuccessUrl("/login?logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll();

		return http.build();
	}

}
