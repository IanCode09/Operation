package com.carefast.careops.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InsysUtils {

    @Value("${insys.credentials}")
    private String credentials;

    public boolean apiKeyValidate(String apiKey) {
         if (apiKey.equals(credentials)) {
             return true;
         } else {
             return false;
         }
    }
}
