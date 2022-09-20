package com.williamramos.cursoalgaworks.api.exception;

public class FieldProblem {

        private String field;
        private String userMessage;

        public String getField() {
            return field;
        }

        public FieldProblem setField(String field) {
            this.field = field;
            return this;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public FieldProblem setUserMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

}
