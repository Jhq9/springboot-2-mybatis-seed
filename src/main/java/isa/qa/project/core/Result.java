package isa.qa.project.core;

import io.swagger.annotations.ApiModelProperty;
import isa.qa.project.enums.ResultCodeEnums;

/**
 * @author jinhuaquan
 * @create 2017年12月11日10:49:52
 * @desc 统一API响应结果封装
 */
public class Result<T> {

    /**
     * 接口响应状态码
     */
    @ApiModelProperty(value = "接口响应状态码，同HTTP状态码规则")
    private int code;

    /**
     * 接口响应描述信息
     */
    @ApiModelProperty(value = "接口响应描述信息")
    private String message;

    /**
     * 接口响应返回数据
     */
    @ApiModelProperty(value = "接口响应返回数据")
    private T data;

    public Result setCode(ResultCodeEnums resultCodeEnum) {
        this.code = resultCodeEnum.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
