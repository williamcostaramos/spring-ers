package com.williamramos.cursoalgaworks.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Problem {
    private int status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime timestamp;

    public Problem status(int status) {
        this.status = status;
        return this;
    }

    public Problem type(String type) {
        this.type = type;
        return this;
    }

    public Problem title(String title) {
        this.title = title;
        return this;
    }

    public Problem detail(String detail) {
        this.detail = detail;
        return this;
    }

    public Problem userMessage(String userMessage) {
        this.userMessage = userMessage;
        return this;
    }

    public Problem timestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

