package com.booking.error;

import org.springframework.validation.Validator;

import java.time.LocalDateTime;

public interface IntervalValidation extends Validator {

    Boolean isValid(LocalDateTime start, LocalDateTime end);


}
