package com.spring.adminlte.dto;

public class ReturnYNDto {

    private  boolean result;
    private  String resultMessage;

    public ReturnYNDto(boolean result, String resultMessage) {
        this.result = result;
        this.resultMessage = resultMessage;
    }

    public ReturnYNDto() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return "ReturnYNDto{" +
                "result=" + result +
                ", resultMessage='" + resultMessage + '\'' +
                '}';
    }
}
