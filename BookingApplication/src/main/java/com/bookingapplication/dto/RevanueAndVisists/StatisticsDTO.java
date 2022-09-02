package com.bookingapplication.dto.RevanueAndVisists;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDTO {
    private List<?> revanueByYears;
    private List<?> revanueByMonths;

    private List<?> visitsByYears;
    private List<?> visitsByMonths;

    public StatisticsDTO(List<?> revanueByYears, List<?> revanueByMonths, List<?> visitsByYears, List<?> visitsByMonths) {
        this.revanueByYears = revanueByYears;
        this.revanueByMonths = revanueByMonths;
        this.visitsByYears = visitsByYears;
        this.visitsByMonths = visitsByMonths;
    }

    public StatisticsDTO() {
    }

    public List<?> getRevanueByYears() {
        return revanueByYears;
    }

    public void setRevanueByYears(List<?> revanueByYears) {
        this.revanueByYears = revanueByYears;
    }

    public List<?> getRevanueByMonths() {
        return revanueByMonths;
    }

    public void setRevanueByMonths(List<?> revanueByMonths) {
        this.revanueByMonths = revanueByMonths;
    }

    public List<?> getVisitsByYears() {
        return visitsByYears;
    }

    public void setVisitsByYears(List<?> visitsByYears) {
        this.visitsByYears = visitsByYears;
    }

    public List<?> getVisitsByMonths() {
        return visitsByMonths;
    }

    public void setVisitsByMonths(List<?> visitsByMonths) {
        this.visitsByMonths = visitsByMonths;
    }

}
