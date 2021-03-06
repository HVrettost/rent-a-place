package com.rentaplace.functionaltests.dto

class UnitCreationRequestDto {

    private final String region
    private final String title
    private final BigDecimal price

    UnitCreationRequestDto(String region, String title, BigDecimal price) {
        this.region = region
        this.title = title
        this.price = price
    }

    String getRegion() {
        this.region
    }

    String getTitle() {
        this.title
    }

    BigDecimal getPrice() {
        this.price
    }
}
