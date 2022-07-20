package com.bookingapplication.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidation {
    public boolean isDateBeforeToday (LocalDate date){
        return date.isBefore(LocalDate.now());
    }

    public boolean isFirstBeforeSecondDate (LocalDate first, LocalDate second){
        return first.isBefore(second);
    }
}
