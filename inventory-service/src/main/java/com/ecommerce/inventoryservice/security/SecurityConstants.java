package com.ecommerce.inventoryservice.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/inventory/public/**";
    public static final String ADMIN_URL = "/api/inventory/admin/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "MyJWTSecret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
