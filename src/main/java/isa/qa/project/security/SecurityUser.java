package isa.qa.project.security;

import isa.qa.project.model.Role;
import isa.qa.project.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *  Security user which contains account info and role info
 *
 *  @author    May
 *  @date      2018/11/21 21:01
 *  @version   1.0
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = -1389416987247719584L;

    /**
     * Role info
     */
    private Role role;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return getPhone();
    }

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
        return getIsEnabled();
    }
}
