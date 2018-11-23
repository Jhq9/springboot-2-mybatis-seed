package isa.qa.project.enums;

/**
 *  响应码枚举，参考HTTP状态码的语义
 *
 *  @author    May
 *  @date      2018/9/3 17:34
 *  @version   1.0
 */
public enum ResultCodeEnums {

    /**
     * 请求已成功
     */
    OK(200),

    /**
     * 请求已经被实现，而且有一个新的资源已经依据请求的需要而建立（POST请求-新建）
     */
    CREATED(201),

    /**
     * 明显的客户端错误（参数错误）
     */
    BAD_REQUEST(400),

    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),

    /**
     * 服务器已经理解请求，但是拒绝执行它（无权限）
     */
    FORBIDDEN(403),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误（代码缺陷错误）
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCodeEnums(int code) {
        this.code = code;
    }
}
