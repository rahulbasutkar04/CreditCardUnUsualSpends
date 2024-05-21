package com.amaap.creditcardunusualspends.controller.dto;

import java.util.Objects;

public class Response {

    private final Http http;
    private  final String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response)) return false;
        return http == response.http && Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(http, message);
    }

    public Response(Http http, String message) {
        this.http=http;
        this.message=message;
    }
}
