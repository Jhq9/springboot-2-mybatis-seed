package isa.qa.project.dto.request;

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
public class UserRequestDTO {

    /**
     * Account's username(nickname)
     */
    private String username;

    /**
     * Account's contact email
     */
    @Email(message = "邮箱格式错误")
    private String email;
}
