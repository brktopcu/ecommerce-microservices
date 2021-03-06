package com.ecommerce.accountservice.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/account/public/**";
    public static final String ADMIN_URL = "/api/account/admin/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "MyJWTSecret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3_600_000; //60 minutes
}
