package poc.springboot.multipleds;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import poc.springboot.multipleds.post.Post;
import poc.springboot.multipleds.post.service.PostService;
import poc.springboot.multipleds.subscriber.Subscriber;
import poc.springboot.multipleds.subscriber.service.SubscriberService;

@SpringBootApplication
@EnableConfigurationProperties
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostService postService, SubscriberService subscriberService)
	{
		return args ->
		{
			List<Post> posts = postService.findAll();
			System.out.println(posts);

			List<Subscriber> subscribers = subscriberService.findAll();
			System.out.println(subscribers);
		};
	}

	@Bean
	CommandLineRunner dsCommandLineRunner(@Qualifier("blogDataSource") DataSource blogDataSource, @Qualifier("subscriberDataSource") DataSource subscriberDataSource)
	{
		return args ->
		{
			// both print out jdbc:h2:mem:blog
			System.out.println(blogDataSource.getConnection().getMetaData().getURL());
			System.out.println(subscriberDataSource.getConnection().getMetaData().getURL());
		};
	}

}
