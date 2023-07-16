package com.orion.ops.framework.common.constant;

import com.orion.lang.define.wrapper.CodeInfo;
import com.orion.lang.define.wrapper.HttpWrapper;
import com.orion.lang.utils.Exceptions;
import com.orion.lang.utils.Strings;

/**
 * 错误码
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023/7/6 16:14
 */
@SuppressWarnings("ALL")
public enum ErrorCode implements CodeInfo {

    BAD_REQUEST(400, "参数验证失败"),

    UNAUTHORIZED(401, "当前认证信息已失效, 请重新登录"),

    FORBIDDEN(403, "无操作权限"),

    NOT_FOUND(404, "未找到该资源"),

    METHOD_NOT_ALLOWED(405, "不支持此方法"),

    REQUEST_TIMEOUT(408, "处理超时"),

    CONFLICT(409, "状态发生改变, 请刷新后重试"),

    PAYLOAD_TOO_LARGE(413, "请求过大"),

    LOCKED(423, "当前已被锁定"),

    TOO_MANY_REQUESTS(429, "请求过快"),

    INTERNAL_SERVER_ERROR(500, "系统异常"),

    // -------------------- 自定义 - 业务 --------------------

    OTHER_DEVICE_LOGIN(700, "该账号于 {} 已在其他设备登陆 {}({})"),

    // -------------------- 自定义 - 通用 --------------------

    NETWORK_FLUCTUATION(900, "当前环境网路波动"),

    HTTP_API(901, "api 调用异常"),

    IO_EXCEPTION(902, "网络异常"),

    SQL_EXCEPTION(903, "数据异常"),

    SFTP_EXCEPTION(904, "操作失败"),

    EXCEL_PASSWORD_ERROR(905, "文档密码错误"),

    PASER_FAILED(906, "解析失败"),

    ENCRYPT_ERROR(907, "数据加密异常"),

    DECRYPT_ERROR(908, "数据解密异常"),

    EXPRESSION_ERROR(909, "表达式错误"),

    TASK_EXECUTE_ERROR(910, "任务执行异常"),

    CONNECT_ERROR(911, "建立连接失败"),

    INTERRUPT_ERROR(912, "操作中断"),

    UNSAFE_OPERATOR(913, "不安全的操作"),

    VCS_OPETATOR_ERROR(914, "仓库操作执行失败"),

    DIABLED_ERROR(915, "数据已被禁用"),

    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.wrapper = HttpWrapper.of(this);
    }

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    private final HttpWrapper<?> wrapper;

    /**
     * 获取 wapper
     *
     * @param data data
     * @return HttpWrapper
     */
    public HttpWrapper<?> wrapper() {
        return HttpWrapper.of(this);
    }

    /**
     * 获取 wapper
     *
     * @param data data
     * @param <T>  T
     * @return HttpWrapper
     */
    public <T> HttpWrapper<T> wrapper(T data) {
        return HttpWrapper.of(this, data);
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    /**
     * @return 获取单例 wapper
     */
    public HttpWrapper<?> getWrapper() {
        return wrapper;
    }

    /**
     * @return 获取异常
     */
    public RuntimeException exception() {
        return Exceptions.httpWrapper(this);
    }

    /**
     * @param params 错误信息参数
     * @return 获取异常
     */
    public RuntimeException exception(Object... params) {
        return Exceptions.httpWrapper(this.toHttpWrapper().msg(Strings.format(this.message, params)));
    }

}
