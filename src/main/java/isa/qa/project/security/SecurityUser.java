package isa.qa.project.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import isa.qa.project.converters.LocalDateTimeToLongConverter;
import isa.qa.project.vo.RoleVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

/**
 *  Security user which contains account info and role info
 *
 *  @author    May
 *  @date      2018/11/21 21:01
 *  @version   1.0
 */
@Getter
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -1389416987247719584L;
    /**
     * 用户的ID
     */
    @JsonIgnore
    @JsonSerialize(using = ToStringSerializer.class)
    private final Long id;

    /**
     * 用户的账号
     */
    private final String username;

    /**
     * 用户的姓名
     */
    private final String nickname;

    /**
     * 用户的密码
     */
    @JsonIgnore
    private final String password;

    /**
     * 用户的手机号
     */
    private final String phoneNumber;

    /**
     * 用户的邮箱地址
     */
    private final String email;

    /**
     * 是否启用
     */
    private final boolean isEnabled;

    /**
     * 上次密码更新时间
     */
    private final Date lastPasswordResetTime;

    /**
     * 注册时间
     */
    @JsonSerialize(using = LocalDateTimeToLongConverter.class)
    private final LocalDateTime createTime;

    /**
     * 用户信息上次更新时间
     */
    @JsonSerialize(using = LocalDateTimeToLongConverter.class)
    private final LocalDateTime updateTime;

    /**
     * 用户的角色
     */
    private final RoleVO role;

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
