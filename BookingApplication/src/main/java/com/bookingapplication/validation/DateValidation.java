package com.bookingapplication.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidation {
    public boolean isDateBeforeToday (LocalDate date){
        return date.isBefore(LocalDate.now());
    }
    //Da li je prvi pre drugog ili da li su istog dana (vazi za jednodnevne rezervacije)
    public boolean isFirstBeforeSecondDate (LocalDate first, LocalDate second){
        return first.isBefore(second) || first.isEqual(second);
    }
    public boolean isDateThreeDaysBeforeToday(LocalDate date){
        int compareValue = LocalDate.now().compareTo(date);
        if(compareValue <= 3) {
            return false;}
        return  true;
    }
}
