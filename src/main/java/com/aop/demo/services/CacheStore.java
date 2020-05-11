package com.aop.demo.services;

import org.springframework.stereotype.Service;

/**
 * This can be of any type.
 * Based on functional requirements, infra availability
 * Probable options : Redis Cache, Mongo Store
 * (access Key might be stored in User Collection) or even SQL DB as per requirements
 */
@Service
public class CacheStore {

    public void putIn(String functionKey, String accessToken, int duration) {
        // create cache identifier as an amalgamation of functionName and accessToken
    }

    public boolean isCached(String functionKey, String accessToken) {
        // DIY check from cache store if it is still present
        // normally the value should auto expire after duration
        // and returned as false since it would not exist
        return true;
    }
}
