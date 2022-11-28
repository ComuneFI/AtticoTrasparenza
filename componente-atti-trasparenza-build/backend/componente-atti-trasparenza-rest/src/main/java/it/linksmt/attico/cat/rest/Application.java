package it.linksmt.attico.cat.rest;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import it.linksmt.attico.cat.rest.filter.CorsFilter;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Configuration
@ComponentScan(basePackages = { "it.linksmt.attico.cat" })
@PropertySources({ @PropertySource("classpath:application.properties"), @PropertySource(value = "file:${attico.cat.properties.path}", ignoreResourceNotFound = true) })
@SpringBootApplication
@EnableWebMvc
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		Filter myFilter = new CorsFilter();
		beanFactory.autowireBean(myFilter);
		registration.setFilter(myFilter);
		registration.addUrlPatterns("/*");
		return registration;
	}
}