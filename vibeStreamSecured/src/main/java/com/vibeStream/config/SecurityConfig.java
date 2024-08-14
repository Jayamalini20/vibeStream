package com.vibeStream.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


		http
		.csrf(csrf -> csrf
				.ignoringRequestMatchers("/createOrder", "/payment-success", "/pay", "/payment-failure", "/verify") // Allow CSRF to be bypassed for this endpoint
				)
		.authorizeHttpRequests(authorizeRequests ->
		authorizeRequests
		.requestMatchers("/", "/login", "/register", "/css/**", "/js/**").permitAll()
		.requestMatchers("/addSong").hasRole("ADMIN")  // Only admins can access /addSong
		.requestMatchers("/viewSongs", "/createPlaylist", "/addPlaylist", "/viewPlaylists", "/viewPlaylist/{id}").hasAnyRole("ADMIN", "CUSTOMER")  // Both admins and customers can view songs
		.anyRequest().authenticated()
				)

		.formLogin(formLogin ->
		formLogin
		.loginPage("/login")
		.loginProcessingUrl("/validate")
		.defaultSuccessUrl("/validate", true) 
		.failureUrl("/login?error=true")
		.permitAll()
				)

		.logout(logout -> 
		logout
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login")
				);

		return http.build();
	}




	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
