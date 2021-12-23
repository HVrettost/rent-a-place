package com.blueground.domain

class Unit {

    private UUID unitId;
    private String imageUrl;
    private String title;
    private String region;
    private String description;
    private Boolean cancellationPolicy;
    private BigDecimal price;
    private Integer averageScore;

    UUID getUnitId() {
        this.unitId;
    }

    String getImageUrl() {
        this.imageUrl;
    }

    String getTitle() {
       this.title;
    }

    String getRegion() {
        this.region;
    }

    String getDescription() {
        this.description;
    }

    Boolean getCancellationPolicy() {
        this.cancellationPolicy;
    }

    BigDecimal getPrice() {
        this.price;
    }

    Integer getAverageScore() {
        this.averageScore;
    }

    void setUnitId(UUID unitId) {
        this.unitId = unitId;
    }

    void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setRegion(String region) {
        this.region = region;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setCancellationPolicy(Boolean cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }
}
