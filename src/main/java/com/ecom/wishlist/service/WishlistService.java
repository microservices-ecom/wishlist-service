package com.ecom.wishlist.service;

import com.ecom.wishlist.dto.AddToWishlistRequest;
import com.ecom.wishlist.dto.WishlistItemResponse;

import java.util.List;

public interface WishlistService {

	WishlistItemResponse add(AddToWishlistRequest request);

	void remove(Long productId);

	List<WishlistItemResponse> list();
}
