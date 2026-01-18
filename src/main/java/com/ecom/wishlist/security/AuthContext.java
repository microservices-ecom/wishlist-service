package com.ecom.wishlist.security;

import org.springframework.stereotype.Component;

@Component
public class AuthContext {

	public Long getCurrentUserId() {
		// In real scenario decode JWT via filter
		return 101L; // mocked for now
	}
}
