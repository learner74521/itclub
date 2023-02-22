package com.itclub.model.sms.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 功能描述
 *
 * @author: onePiece
 */
public class AliyunSmsDTO {

    @SerializedName("BizId")
    private String bizId;
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("RequestId")
    private String requestId;
    private int httpStatus;
    private String sendCode;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    @Override
    public String toString() {
        return "SendSmsResponse{" +
                "bizId='" + bizId + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", requestId='" + requestId + '\'' +
                ", httpStatus=" + httpStatus +
                ", sendCode='" + sendCode + '\'' +
                '}';
    }
}
