package com.williamramos.cursoalgaworks.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Problem {
    private int status;
    private String type;
    private String title;
    private String detail;
    private List<FieldProblem> fields =new ArrayList<>();

    public Problem() {
    }

    public Problem(int status, String type, String title, String detail, List<FieldProblem> fields, String userMessage, LocalDateTime timestamp) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.fields = fields;
        this.userMessage = userMessage;
        this.timestamp = timestamp;
    }

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

    public Problem problemList(List<FieldProblem> problemList) {
        this.fields = problemList;
        return this;
    }

    public List<FieldProblem> getFields() {
        return fields;
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

