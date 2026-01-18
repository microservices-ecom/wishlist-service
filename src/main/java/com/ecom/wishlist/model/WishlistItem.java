package com.ecom.wishlist.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist_items", uniqueConstraints = @UniqueConstraint(columnNames = { "userId", "productId" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	private Long productId;

	private LocalDateTime addedAt;
}
