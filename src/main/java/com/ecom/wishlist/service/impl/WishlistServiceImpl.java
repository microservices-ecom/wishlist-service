package com.ecom.wishlist.service.impl;


import com.ecom.wishlist.client.ProductClient;
import com.ecom.wishlist.event.WishlistEvent;
import com.ecom.wishlist.event.WishlistEventPublisher;
import com.ecom.wishlist.exception.ResourceConflictException;
import com.ecom.wishlist.exception.ResourceNotFoundException;
import com.ecom.wishlist.mapper.WishlistMapper;
import com.ecom.wishlist.dto.AddToWishlistRequest;
import com.ecom.wishlist.dto.WishlistItemResponse;
import com.ecom.wishlist.model.WishlistItem;
import com.ecom.wishlist.repository.WishlistRepository;
import com.ecom.wishlist.security.AuthContext;
import com.ecom.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final WishlistRepository repository;
	private final ProductClient productClient;
	private final WishlistEventPublisher eventPublisher;
	private final WishlistMapper mapper;
	private final AuthContext auth;

	@Override
	public WishlistItemResponse add(AddToWishlistRequest request) {
		var userId = auth.getCurrentUserId();

		if (!productClient.productExists(request.productId())) {
			throw new ResourceNotFoundException("Product not found");
		}

		repository.findByUserIdAndProductId(userId, request.productId()).ifPresent(i -> {
			throw new ResourceConflictException("Already in wishlist");
		});

		WishlistItem item = WishlistItem.builder().userId(userId).productId(request.productId())
				.addedAt(LocalDateTime.now()).build();

		repository.save(item);
		eventPublisher.publish(new WishlistEvent(userId, request.productId(), "ADDED"));

		return mapper.toResponse(item);
	}

	@Override
	public void remove(Long productId) {
		var userId = auth.getCurrentUserId();
		var item = repository.findByUserIdAndProductId(userId, productId)
				.orElseThrow(() -> new ResourceNotFoundException("Not in wishlist"));

		repository.delete(item);
		eventPublisher.publish(new WishlistEvent(userId, productId, "REMOVED"));
	}

	@Override
	public List<WishlistItemResponse> list() {
		var userId = auth.getCurrentUserId();
		return repository.findByUserId(userId).stream().map(mapper::toResponse).toList();
	}
}
