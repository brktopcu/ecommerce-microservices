package com.ecommerce.orderservice.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/order/public/**";
    public static final String ADMIN_URL = "/api/order/admin/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "MyJWTSecret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
