package com.finstream.common.response;

public class GenericResponse<T> {

    private T data;
    private boolean success;
    private String msg;

    public GenericResponse() {
    }

    public GenericResponse(T data, boolean success, String msg) {
        this.data = data;
        this.success = success;
        this.msg = msg;
    }

    public static <T> GenericResponse<T> success(T data, String msg) {
        return new GenericResponse<>(data, true, msg);
    }

    public static <T> GenericResponse<T> failure(String msg) {
        return new GenericResponse<>(null, false, msg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}