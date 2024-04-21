package com.tool.web;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 封装web请求的响应数据
 *
 * @param <T> 实体类
 */
@Data
@Accessors(chain = true)
public class ResponseData<T> {
    private String code;        //http状态码
    private String message;     //返回信息
    private T data;             //返回数据
    private long timestamp;     //时间戳

    public ResponseData() {
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    /**
     * 封装成功响应数据
     *
     * @param data 实体类
     * @param <T>
     * @return 成功状态数据
     * <code>
     * {
     * "code" : "200",
     * "message" : "success",
     * "data" : json格式数据,
     * "timestamp" : 当前系统时间(毫秒)
     * }
     * </code>
     */
    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>("200", "success", data);
    }

    /**
     * 封装失败响应数据
     *
     * @param message 错误信息
     * @param <T>
     * @return 失败状态数据
     * <code>
     * {
     * "code" : "500",
     * "message" : 错误信息,
     * "data" : null,
     * "timestamp" : 当前系统时间(毫秒)
     * }
     * </code>
     */
    public static <T> ResponseData<T> fail(String message) {
        return new ResponseData<>("500", message, null);
    }

    /**
     * 封装自定义响应数据, 状态码推荐使用枚举, 如: HttpStatus.OK.value()
     *
     * @param code    状态码
     * @param message 信息
     * @param data    返回数据
     * @param <T>
     * @return 自定义响应数据
     * <code>
     * {
     * "code" : 状态码,
     * "message" : 信息,
     * "data" : 返回数据,
     * "timestamp" : 当前系统时间(毫秒)
     * }
     * </code>
     */
    public static <T> ResponseData<T> other(String code, String message, T data) {
        return new ResponseData<>(code, message, data);
    }
}
