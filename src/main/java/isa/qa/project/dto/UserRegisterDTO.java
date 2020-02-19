package isa.qa.project.dto;

import io.swagger.annotations.ApiModelProperty;
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
public class UserRegisterDTO {

    /**
     * User' role id
     */
    @ApiModelProperty(value = "用户角色的ID")
    @NotNull(message = "注册用户的角色不能为空")
    private Long roleId;

    /**
	 * Register phone number
	 */
	@ApiModelProperty(value = "注册的手机号")
	@NotEmpty(message = "注册手机号不能为空")
	@Pattern(regexp = PHONE_REGEX, message = "手机格式错误")
	private String phoneNumber;

    /**
     * The verify code sent to the phone (Cached)
     */
    @ApiModelProperty(value = "手机号验证码")
    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;

    /**
     * Contact email
     */
    @ApiModelProperty(value = "用户邮箱")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * Account's login password
     */
    @ApiModelProperty(value = "用户的登录密码MD5加密")
    @NotEmpty(message = "登录密码不能为空")
    @Size(min = 32, max = 32, message = "密码需要进行MD5加密")
    private String password;

    /**
     * Account's nickname
     */
    @ApiModelProperty(value = "用户的昵称")
    private String username;
}
