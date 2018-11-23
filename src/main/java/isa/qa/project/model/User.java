package isa.qa.project.model;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Foreign key of role
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * Username
     */
    @Column(name = "name")
    private String name;

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
     * Account registerUser time
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * Account updated time
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * Last password reset time
     */
    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;
}
