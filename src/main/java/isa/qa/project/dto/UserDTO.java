package isa.qa.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 *  User request transport object
 *
 *  @author    May
 *  @date      2018/11/22 9:40
 *  @version   1.0
 */
@Data
public class UserDTO {

    /**
     * Account's username(nickname)
     */
    @ApiModelProperty(value = "用户的账号")
    private String username;

    /**
     * Account's contact email
     */
    @ApiModelProperty(value = "用户的邮箱地址")
    @Email(message = "邮箱格式错误")
    private String email;
}
