package com.ecom.wishlist.event;


public record WishlistEvent(Long userId, Long productId, String action) {}
