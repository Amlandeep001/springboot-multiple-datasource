package poc.springboot.multipleds.subscriber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import poc.springboot.multipleds.subscriber.Subscriber;

@Service
public class SubscriberService
{

	private final JdbcClient jdbcClient;

	public SubscriberService(@Qualifier("subscriberJdbcClient") JdbcClient jdbcClient)
	{
		this.jdbcClient = jdbcClient;
	}

	public List<Subscriber> findAll()
	{
		return jdbcClient.sql("select id,name,email from Subscriber")
				.query(Subscriber.class)
				.list();
	}

}
