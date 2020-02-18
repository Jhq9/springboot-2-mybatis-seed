package isa.qa.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  User login request transport object
 *
 *  @author    May
 *  @date      2018/11/22 9:45
 *  @version   1.0
 */
@Data
public class UserLoginDTO {

    /**
     * Account's phone
     */
    @ApiModelProperty(value = "用户的手机号")
    private String phone;

    /**
     * Account's login password(md5 encoded)
     */
    @ApiModelProperty(name = "用户的密码")
    private String password;
}
