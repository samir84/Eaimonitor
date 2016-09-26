package com.hs.eai.monitor.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@ComponentScan(basePackages = { "com.hs.eai.monitor" })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;

	// @Autowired
	// private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	// @Autowired
	// private LogoutSuccessHandler myLogoutSuccessHandler;

	// @Autowired
	// private AuthenticationFailureHandler authenticationFailureHandler;

	public SecurityConfig() {
		super();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable().authorizeRequests().antMatchers("/login*", "/logout*", "/register*" ,"/user/forgotPassword*","/user/resetPassword*","/user/changePassword*","/user/updatePassword*").permitAll()
				// .antMatchers("/invalidSession*").anonymous()
				.anyRequest().authenticated().and().formLogin().loginPage("/login.html").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/home.html").failureUrl("/login.html?error=true")
				// .successHandler(myAuthenticationSuccessHandler)
				// .failureHandler(authenticationFailureHandler)
				.permitAll().and().sessionManagement().invalidSessionUrl("/invalidSession.html").sessionFixation()
				.none().and().logout()
				// .logoutSuccessHandler(myLogoutSuccessHandler)
				.invalidateHttpSession(false).logoutSuccessUrl("/logout.html?logSucc=true").deleteCookies("JSESSIONID")
				.permitAll().and().rememberMe().rememberMeServices(rememberMeServices());

	}

	@Bean
	public AbstractRememberMeServices rememberMeServices() {
		PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
				"EaiMonitorRememberMeKey", userDetailsService, persistentTokenRepository());
		rememberMeServices.setAlwaysRemember(true);
		rememberMeServices.setCookieName("remember-me-posc");
		rememberMeServices.setTokenValiditySeconds(1209600);//2 weeks
		return rememberMeServices;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
}