package com.carefast.careops.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carefast.careops.security.SecurityUtils;

import java.util.Date;

public class TokenUtils {

    public static String generateRefreshToken() {
        return JWT.create()
                .withSubject("refresh")
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityUtils.REFRESH_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityUtils.REFRESH_SECRET.getBytes()));
    }

    public static String generateToken(String subject) {
        return JWT.create()
                .withSubject(subject)
//                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityUtils.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityUtils.SECRET.getBytes()));
    }
}
