package com.itp.ITPJan2026Springboot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationResponse {
	
	private final String JWT;
}
