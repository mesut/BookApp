package com.n11.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * Spring MVC {@link org.springframework.context.annotation.Configuration}. Extends {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport}, which provides convenient callbacks that allow
 * customization of aspects of the Spring Web MVC framework. Callbacks allow registering custom interceptors, message converters, argument
 * resolvers, a validator, resource handling, etc.
 */
@Configuration
// @EnableCaching
// @ImportResource("classpath:services.xml")
@ComponentScan(basePackages = {"com.n11"}, includeFilters = @ComponentScan.Filter({ Controller.class }), excludeFilters = @ComponentScan.Filter({ Configuration.class }))
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public WebMvcConfiguration() {
		LOGGER.info("init");
	}

	/**
	 * Added <tt>favicon.ico</tt> support per <a
	 * href="http://www.jiwhiz.com/post/2012/10/Add_Favicon_Support_To_Spring_MVC_Application">jiwhiz.com</a>.
	 * <p/>
	 * Added JavaScript dependency management support per <a href="http://www.webjars.org/documentation#Spring%20MVC">WebJars Spring MVC
	 * example</a>.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String[] staticResourcesLocations = { "/resources/", "classpath:/META-INF/web-resources/" };
		String[] rootResourceLocation = { "/" };

		registry.addResourceHandler("/resources/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("/resources/**").addResourceLocations(staticResourcesLocations);
		// .setCachePeriod(0); // disable caching

		registry.addResourceHandler("/favicon.ico").addResourceLocations(rootResourceLocation);
		registry.addResourceHandler("/robots.txt").addResourceLocations(rootResourceLocation);
		registry.addResourceHandler("/index.*").addResourceLocations(rootResourceLocation);

		registry.addResourceHandler("/docs/**").addResourceLocations("/docs/", "classpath:/META-INF/docs/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		// registry.addViewController("/login"); //TODO
		// registry.addViewController("/error"); //TODO
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();

		mapper.getSerializationConfig()//
				.with(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)//
				.with(SerializationFeature.INDENT_OUTPUT)//
				.shouldSortPropertiesAlphabetically();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper;
	}

	/**
	 * Configure Spring to use the a common {@link com.fasterxml.jackson.databind.ObjectMapper} for HTTP message conversion.
	 * <p>
	 * If using RestTemplate, be sure to register the {@link org.springframework.http.converter.HttpMessageConverter}s with {@code RestTemplate} per <a
	 * href="http://stackoverflow.com/a/9371244/237225">this link</a>.
	 */
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		final MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setObjectMapper(objectMapper());
		LOGGER.debug("add a default Jackson2 converter: {}", jacksonConverter);
		converters.add(jacksonConverter);
		// restTemplate().setMessageConverters(jacksonConverters);
	}

}
