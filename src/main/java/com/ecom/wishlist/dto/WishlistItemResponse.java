package com.ecom.wishlist.dto;


import java.time.LocalDateTime;

public record WishlistItemResponse(Long id, Long productId, LocalDateTime addedAt) {}
