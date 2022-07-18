package com.bookingapplication.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ImageValidation {
    public boolean validateImageFile (MultipartFile file) {
        String type = file.getContentType();
        assert type != null;
        type = type.split("/")[0];
        return type.equals("image");
    }
}
