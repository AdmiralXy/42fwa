package com.admiralxy.cinema.models;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class AuthLog {

    private String date;

    private String time;

    private String address;

    public AuthLog(String address) {
        LocalDateTime dateTime = LocalDateTime.now();
        date = dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.US));
        time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.US));
        this.address = address;
    }
}
