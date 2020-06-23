package com.spring.adminlte.core.template.classes;

public class ResponseData <H, T> {
    private H header;
    private T body;

    public ResponseData(H header, T body) {
        this.header = header;
        this.body = body;
    }

    public ResponseData() {
    }

    public H getHeader() {
        return header;
    }

    public void setHeader(H header) {
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
        return "ResponseData{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
