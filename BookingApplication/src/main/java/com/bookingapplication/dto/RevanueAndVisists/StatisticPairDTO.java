package com.bookingapplication.dto.RevanueAndVisists;

public class StatisticPairDTO {
    private String key;
    private Double value;

    public StatisticPairDTO(String key, Double value) {
        this.key = key;
        this.value = value;
    }

    public StatisticPairDTO() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
