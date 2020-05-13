package com.spring.adminlte.templatesDto;

import com.spring.adminlte.dto.HeaderDto;

public class RequestData<T> {
    private HeaderDto header;
    private T body;

    public HeaderDto getHeader() {
        return header;
    }

    public void setHeader(HeaderDto header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}