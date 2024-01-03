package com.gcxy.config;

public final class R<T> {
    private int code;
    private String msg;
    private T data;

    public R() {

    }

    public R(int code) {
        this.code = code;
        this.msg = "";
        this.data = null;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R Success(Object data) {
        return new R(ResultCondeEnum.SUCCESS.getCode(), ResultCondeEnum.SUCCESS.getMessage(), data);
    }

    public static R Success(String message, Object data) {
        return new R(ResultCondeEnum.SUCCESS.getCode(), message, data);
    }

    public static R Success() {
        return Success("");
    }

    public static R Failed(String msg) {
        return new R(ResultCondeEnum.SYSTEM_EXCEPTION.getCode(), msg);
    }

    public static R Failed() {
        return Failed("Failed");
    }

    public static R Failed(int code, String msg) {
        return new R(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean succeeded() {
        return getCode() == ResultCondeEnum.SUCCESS.getCode();
    }


    public boolean failed() {
        return getCode() != ResultCondeEnum.SUCCESS.getCode();
    }

}

