package com.bobocode.client;

import org.springframework.stereotype.Component;

@Component
public class NameProvider {
    private static final String CHUCK_NORRIS = "Chuck Norris";

    public String getName() {
        return CHUCK_NORRIS;
    }
}
