package isa.qa.project.model;

import isa.qa.project.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *  用户
 *
 *  @author    May
 *  @date      2018/11/21 16:31
 *  @version   1.0
 */
@Data
@Table(name = "i_user")
public class User extends BaseEntity {

    /**
     * Foreign key of role
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 用户的账号
     */
    @Column(name = "username")
    private String username;

    /**
     * nickname
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * User's phone
     */
    @Column(name = "phone")
    private String phone;

    /**
     * User's email
     */
    @Column(name = "email")
    private String email;

    /**
     * User's encoded password (PasswordEncoder.encode(md5(password)))
     */
    @Column(name = "password")
    private String password;

    /**
     * Account enabled status: true-enabled, false-locked
     */
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    /**
     * Last password reset time
     */
    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;
}
