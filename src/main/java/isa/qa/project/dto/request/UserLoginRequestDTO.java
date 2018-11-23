package isa.qa.project.dto.request;

import lombok.Data;

/**
 *  User login request transport object
 *
 *  @author    May
 *  @date      2018/11/22 9:45
 *  @version   1.0
 */
@Data
public class UserLoginRequestDTO {

    /**
     * Account's phone
     */
    private String phone;

    /**
     * Account's login password(md5 encoded)
     */
    private String password;
}
