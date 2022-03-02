package com.hyperskill.codesharingplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Document("codeitems")
public class Code {
    @JsonIgnore
    private UUID id = UUID.randomUUID();
    private String code;
    private LocalDateTime date = LocalDateTime.now();
    private long time;
    private long views;

    public Code() {
    }

    public Code(UUID id, String code, LocalDateTime date, long time, long views) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getFormattedDate(){
        String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formattedDateTime = date.format(formatter);
        return formattedDateTime;
    }
}
