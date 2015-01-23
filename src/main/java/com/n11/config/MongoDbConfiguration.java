package com.n11.config;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.n11.repository"})
public class MongoDbConfiguration extends AbstractMongoConfiguration {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static final String MONGO_URI_BEAN_NAME = "mongoURI";

	public MongoDbConfiguration() {
		LOGGER.info("init");
	}

	@SuppressWarnings("deprecation")
	@Bean
	public MongoURI mongoURI() {
		LOGGER.info("connecting to db");
		MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
		assert mongoURI != null : "missing MONGOHQ_URL";
		return mongoURI;
	}

	@Override
	@DependsOn(MONGO_URI_BEAN_NAME)
	@Bean
	protected String getDatabaseName() {
		return mongoURI().getDatabase();
	}

	@SuppressWarnings("deprecation")
	@Override
	@DependsOn(MONGO_URI_BEAN_NAME)
	@Bean
	public Mongo mongo() throws Exception {
		return new Mongo(mongoURI());
	}

}
