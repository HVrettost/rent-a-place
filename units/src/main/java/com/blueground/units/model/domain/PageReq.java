package com.blueground.units.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageReq {

    private final Integer page;
    private final Integer pageSize;
}
