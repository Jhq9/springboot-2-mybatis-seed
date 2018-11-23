package isa.qa.project.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

import static isa.qa.project.constants.RegexStrConstants.PHONE_REGEX;

/**
 *  Account register form data transport object
 *
 *  @author    May
 *  @date      2018/11/21 23:32
 *  @version   1.0
 */
@Data
public class UserRegisterRequestDTO {

    /**
     * User' role id
     */
    @NotNull(message = "注册用户的角色不能为空")
    private Long roleId;

    /**
     * Register phone number
     */
    @NotEmpty(message = "注册手机号不能为空")
    @Pattern(regexp = PHONE_REGEX, message = "手机格式错误")
    private String phone;

    /**
     * The verify code sent to the phone (Cached)
     */
    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;

    /**
     * Contact email
     */
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * Account's login password
     */
    @NotEmpty(message = "登录密码不能为空")
    @Size(min = 32, max = 32, message = "密码需要进行MD5加密")
    private String password;

    /**
     * Account's nickname
     */
    private String username;
}
