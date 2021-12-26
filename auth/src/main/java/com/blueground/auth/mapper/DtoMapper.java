package com.blueground.auth.mapper;

public interface DtoMapper<T, S> {

    T toDto(S s);
}
