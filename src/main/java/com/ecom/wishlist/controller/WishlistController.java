package com.ecom.wishlist.controller;

import com.ecom.wishlist.dto.AddToWishlistRequest;
import com.ecom.wishlist.dto.WishlistItemResponse;
import com.ecom.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

	private final WishlistService service;

	@PostMapping
	public WishlistItemResponse add(@RequestBody AddToWishlistRequest req) {
		return service.add(req);
	}

	@DeleteMapping("/{productId}")
	public void remove(@PathVariable Long productId) {
		service.remove(productId);
	}

	@GetMapping
	public List<WishlistItemResponse> list() {
		return service.list();
	}
}
