package com.n11.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

/**
 * Spring context {@code Configuration} that configures the root Spring context.
 * <p>
 * Does <b>NOT</b> load {@link org.springframework.stereotype.Controller}s; that is the job of {@link WebMvcConfiguration}.
 */
@Configuration
@Import({ MongoDbConfiguration.class })
@PropertySource("classpath:git.properties")
@ComponentScan(basePackages = {"com.n11"}, excludeFilters = @ComponentScan.Filter({ Controller.class, Configuration.class }))
public class WebModuleConfiguration {

	Logger logger = LoggerFactory.getLogger(getClass());

	public WebModuleConfiguration() {
		logger.info("init");
	}

	@Bean(name = "propertySourcesPlaceholderConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@DependsOn("propertySourcesPlaceholderConfigurer")
	public GitRepositoryState gitRepositoryState() {
		return new GitRepositoryState();
	}

}
