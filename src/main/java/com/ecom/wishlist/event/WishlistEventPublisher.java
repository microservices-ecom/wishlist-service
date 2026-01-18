package com.ecom.wishlist.event;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishlistEventPublisher {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void publish(WishlistEvent event) {
		kafkaTemplate.send("wishlist.events", event);
	}
}
