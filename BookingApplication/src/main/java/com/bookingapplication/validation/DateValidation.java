package com.bookingapplication.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateValidation {
    public boolean isDateBeforeToday (LocalDateTime date){
        //ako je danasnji dan, vracace true, jer je date before now
        return date.isBefore(LocalDateTime.now());
    }

    public boolean isFirstBeforeSecondDate (LocalDateTime first, LocalDateTime second){
        return first.isBefore(second);
    }
}
