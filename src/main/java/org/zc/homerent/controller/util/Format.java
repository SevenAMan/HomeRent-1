package org.zc.homerent.controller.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author FDws
 * Created on 2018/6/26 8:32
 */

public class Format {
    /**
     * 处理成功 0, 或者失败 1 的指示器
     */
    private int code = ReturnStatus.SUCCESS;
    /**
     * 处理消息, 只有在 code 为 FAILURE 时有效
     */
    private String message = "";
    /**
     * 处理结果, 只有在 code 为 SUCCESS 时有效
     */
    private Map<String, Object> data = new HashMap<>();

    public Format() {
    }

    public Format code(int code) {
        this.code = code;
        return this;
    }

    public Format message(String message) {
        this.message = message;
        return this;
    }

    public Format addData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @SuppressWarnings("unused")
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Format{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}