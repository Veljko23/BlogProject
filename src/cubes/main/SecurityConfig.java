package cubes.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource myDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(users.username("username").password("password").roles("admin").disabled(true));
			
		
		auth.jdbcAuthentication().dataSource(myDataSource);
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/administration/category-list").hasRole("admin")
		.antMatchers("/administration/category-form").hasRole("admin")
		.antMatchers("/administration/tag-form").hasRole("admin")
		.antMatchers("/administration/tag-list").hasRole("admin")
		.antMatchers("/administration/post-form").hasRole("admin")
		.antMatchers("/administration/post-list").hasRole("admin")
		.antMatchers("/administration/message-list").hasRole("admin")
		.antMatchers("/administration/comment-list").hasRole("admin")
		.antMatchers("/administration/author-list").hasRole("admin")
		.antMatchers("/administration/author-form").hasRole("admin")
		.antMatchers("/administration/slider-list").hasRole("admin")
		.antMatchers("/administration/slider-form").hasRole("admin")
		

		.antMatchers("/administration/**").hasRole("admin")
		
		.and().formLogin()
		.loginPage("/loginPage")
		.loginProcessingUrl("/authenticateTheUser").permitAll()
		.and()
        .csrf().disable();
	}
	
}
