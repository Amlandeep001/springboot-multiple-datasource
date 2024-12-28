package poc.springboot.multipleds.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class JdbcClientConfiguration
{
	@Bean
	JdbcClient blogJdbcClient(@Qualifier("blogDataSource") DataSource dataSource)
	{
		return JdbcClient.create(dataSource);
	}

	@Bean
	JdbcClient subscriberJdbcClient(@Qualifier("subscriberDataSource") DataSource dataSource)
	{
		return JdbcClient.create(dataSource);
	}
}
