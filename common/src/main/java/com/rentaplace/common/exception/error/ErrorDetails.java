package com.rentaplace.common.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorDetails {

    private final int applicationErrorCode;
    private final String description;
}
