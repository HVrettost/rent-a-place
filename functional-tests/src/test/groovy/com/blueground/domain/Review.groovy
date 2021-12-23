package com.blueground.domain

class Review {

    private int score
    private String comment
    private UUID unitId
    private UUID userId

    int getScore() {
        this.score;
    }

    void setScore(int score) {
        this.score = score
    }

    String getComment() {
        this.comment
    }

    void setComment(String comment) {
        this.comment = comment
    }

    UUID getUnitId() {
        this.unitId
    }

    void setUnitId(UUID unitId) {
        this.unitId = unitId
    }

    UUID getUserId() {
        this.userId = userId
    }

    void setUserId(UUID userId) {
        this.userId = userId
    }
}
