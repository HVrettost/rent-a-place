package com.rentaplace.auth.config.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FreeForAllEndpoints {

    GET_UNITS_BASED_ON_SEARCH_TEXT("/rentaplace/v1/units");

    private final String endpoint;
}
