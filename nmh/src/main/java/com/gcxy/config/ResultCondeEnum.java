package com.gcxy.config;

public enum ResultCondeEnum {

    SUCCESS(200, "返回成功"),
    SYSTEM_EXCEPTION(500, "系统异常"),
    REQUEST_PARAM_ERROR(401, "请求参数错误"),
    REQUEST_OUT_OVERTIME(408, "请求超时"),
    REQUEST_NOT_FOUND(404, "请求的资源或服务未找到"),
    REQUEST_LENGTH_LIMIT(414, "请求URI太长"),
    REQUEST_Format_NOT_SUPPORTED(415, "请求的格式不支持"),
    ;
    /**
     * 枚举值
     */
    private final Integer code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>LocalCacheEnum</code>枚举对象
     *
     * @param code    枚举值
     * @param message 枚举描述
     */
    ResultCondeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static boolean isSuccessCode(int code) {
        return code == SUCCESS.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}