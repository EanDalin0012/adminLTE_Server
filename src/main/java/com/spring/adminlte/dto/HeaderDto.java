package com.spring.adminlte.dto;

public class HeaderDto {

    String msg;
    String sessionId;
    String authData;
    String userID;
    String languageCode;
    String channelTypeCode;
    boolean result;

    public HeaderDto(String msg, String sessionId, String authData, String userID, String languageCode, String channelTypeCode, boolean result) {
        this.msg = msg;
        this.sessionId = sessionId;
        this.authData = authData;
        this.userID = userID;
        this.languageCode = languageCode;
        this.channelTypeCode = channelTypeCode;
        this.result = result;
    }

    public HeaderDto() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getChannelTypeCode() {
        return channelTypeCode;
    }

    public void setChannelTypeCode(String channelTypeCode) {
        this.channelTypeCode = channelTypeCode;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HeaderDto{" +
                "msg='" + msg + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", authData='" + authData + '\'' +
                ", userID='" + userID + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", channelTypeCode='" + channelTypeCode + '\'' +
                ", result=" + result +
                '}';
    }
}
