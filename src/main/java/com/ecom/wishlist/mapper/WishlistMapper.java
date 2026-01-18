package com.ecom.wishlist.mapper;


import com.ecom.wishlist.model.WishlistItem;
import com.ecom.wishlist.dto.WishlistItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
	WishlistItemResponse toResponse(WishlistItem item);
}
