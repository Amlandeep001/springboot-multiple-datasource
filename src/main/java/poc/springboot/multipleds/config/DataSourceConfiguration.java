package poc.springboot.multipleds.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(proxyBeanMethods = false)
public class DataSourceConfiguration
{

	// BLOG ===========================================================================================================

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.blog")
	DataSourceProperties blogDataSourceProperties()
	{
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	HikariDataSource blogDataSource(DataSourceProperties blogDataSourceProperties)
	{
		return blogDataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}

	@Bean
	DataSourceScriptDatabaseInitializer blogDataSourceScriptDatabaseInitializer(@Qualifier("blogDataSource") DataSource dataSource)
	{
		var settings = new DatabaseInitializationSettings();
		settings.setSchemaLocations(List.of("classpath:blog-schema.sql"));
		settings.setMode(DatabaseInitializationMode.EMBEDDED);
		return new DataSourceScriptDatabaseInitializer(dataSource, settings);
	}

	// SUBSCRIBERS ====================================================================================================

	@Bean
	@ConfigurationProperties("app.datasource.subscribers")
	DataSourceProperties subscribersDataSourceProperties()
	{
		return new DataSourceProperties();
	}

	@Bean
	DataSource subscriberDataSource(@Qualifier("subscribersDataSourceProperties") DataSourceProperties subscribersDataSourceProperties)
	{
		return DataSourceBuilder
				.create()
				.url(subscribersDataSourceProperties.getUrl())
				.username(subscribersDataSourceProperties.getUsername())
				.password(subscribersDataSourceProperties.getPassword())
				.build();
	}

	@Bean
	DataSourceScriptDatabaseInitializer subscriberDataSourceScriptDatabaseInitializer(@Qualifier("subscriberDataSource") DataSource dataSource)
	{
		var settings = new DatabaseInitializationSettings();
		settings.setSchemaLocations(List.of("classpath:subscriber-schema.sql"));
		settings.setMode(DatabaseInitializationMode.EMBEDDED);
		return new DataSourceScriptDatabaseInitializer(dataSource, settings);
	}

}
