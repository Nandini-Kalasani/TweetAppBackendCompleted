package com.tweetapp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tweetapp.service.impl.CustUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private CustUserDetailsService userDetailsService;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.parentAuthenticationManager(authenticationManagerBean()).userDetailsService(userDetailsService);
		
	}
	
	/* @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	   /* @Override
	    protected void configure(@Autowired AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService)
	          .passwordEncoder(bCryptPasswordEncoder());
	    }
       */
	   /* @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf()
	          .disable()
	          .authorizeRequests()
	          .and()
	          .httpBasic()
	          .and()
	          .authorizeRequests()
	          .anyRequest()
	          .permitAll()
	          .and()
	          .sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    	http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
			http.formLogin();
			http.httpBasic();
	    }
	 */
	 /**
	  you have to disable csrf Protection because it is enabled by default in spring security: 
	  here you can see 
	  code that allow cors origin.
	  */
	 @Override
	    protected void configure(HttpSecurity http) throws Exception{
	        //http.cors().and().csrf().disable();//if I didnt use it getting forbidden error
		 //sample
		 /*http.authorizeRequests()
		 .antMatchers("/login").authenticated()
		 .and().formLogin().and().httpBasic();*/
	    
	    
		 /*http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
			http.formLogin();
			http.httpBasic();*/
	
	
	
	        http.cors().and().csrf().disable()
			
			
			
			.authorizeRequests()
			.antMatchers("/token").permitAll()
			.antMatchers("/api/v1.0/tweets/register").permitAll()
			.antMatchers("/validate/**").permitAll()
			.antMatchers("/api/v1.0/tweets/forgot").permitAll()
			//.anyRequest().authenticated()
			//.antMatchers("/api/v1.0/tweets/login").permitAll()
			
			.anyRequest().authenticated()
		
			
			.and()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors();


http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("*"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	 @Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
	
}
