package poc.springboot.multipleds.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.springboot.multipleds.subscriber.Subscriber;
import poc.springboot.multipleds.subscriber.service.SubscriberService;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController
{
	private final SubscriberService subscriberService;

	public SubscriberController(SubscriberService subscriberService)
	{
		this.subscriberService = subscriberService;
	}

	@GetMapping("/all")
	public List<Subscriber> findAllSubscribers()
	{
		return subscriberService.findAll();
	}
}
