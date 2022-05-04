package com.senko.common.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.HashMap;

//以静态形式引入StatusCodeConstants类的所有属性
import static com.senko.common.constants.HttpStatus.OK;
import static com.senko.common.constants.HttpStatus.InnerError;

/**
 * 前后端交互对象
 *
 * FastJson：自动忽略null值键
 * 继承了HashMap，最终数据的存储方式是
 * map.put(tag, val)
 *  ==>
 * map {
 *     "tag1": "val1",
 *     "tag2": "val2"
 * }
 *
 * @author senko
 * @date 2022/4/25 8:49
 */
@Data
@NoArgsConstructor
public class AjaxResult<T> extends HashMap<Object, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功与否
     */
    public static final String FLAG_TAG = "flag";

    /**
     * 响应码
     */
    public static final String CODE_TAG = "code";

    /**
     * 相应消息
     */
    public static final String MESSAGE_TAG = "message";

    /**
     * 携带数据
     */
    public static final String DATA_TAG = "data";

    /**
     * ==================Constructor=============
     */

    /**
     * @param flag      成功与否
     * @param code      状态码
     * @param message   携带消息
     */
    public AjaxResult(boolean flag, int code, String message) {
        //supper();子类实例化时，默认调用父类的无参构造方法
        super.put(FLAG_TAG, flag);
        super.put(CODE_TAG, code);
        super.put(MESSAGE_TAG, message);
        super.put(DATA_TAG, null);
    }

    /**
     * @param flag      成功与否
     * @param code      状态码
     * @param message   携带消息
     * @param data      相应数据
     */
    public  AjaxResult(boolean flag, int code, String message, final T data) {
        super.put(FLAG_TAG, flag);
        super.put(CODE_TAG, code);
        super.put(MESSAGE_TAG, message);
        super.put(DATA_TAG, data);
    }

    /** ==================Success================= */

    /**
     * 快速相应成功：
     * @return Successful result with null data
     */
    public static AjaxResult success() {
        return AjaxResult.success(OK.getMessage(), null);
    }

    /**
     * 快速相应成功
     * @param message   携带消息
     * @return
     */
    public static AjaxResult success(String message) {
        return AjaxResult.success(message, null);
    }

    /**
     * 快速响应成功
     * @param data      响应数据
     * @param <T>       数据类型
     * @return
     */
    public static <T> AjaxResult<T> success(T data) {
        return AjaxResult.success(OK.getMessage(), data);
    }

    /**
     * 成功：true、{@link com.senko.common.constants.HttpStatus}、message、data
     * @param message   携带消息
     * @param data      相应数据
     * @return
     */
    public static <T> AjaxResult<T> success(String message, final T data) {
        return new AjaxResult<T>(true, OK.getCode(), message, data);
    }

    /**
     * =================ERROR==============
     */

    /**
     * 快速响应失败
     * @param message
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> error(String message) {
        return AjaxResult.error(InnerError.getCode(), message);
    }

    /**
     * 快速响应失败
     * @param code      状态码
     * @param message   错误消息
     * @return
     */
    public static <T> AjaxResult<T> error(int code, String message) {
        return new AjaxResult<T>(false, code, message);
    }
}
