package isa.qa.project.security;

import isa.qa.project.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Get user detail of security service
 *
 *  @author    May
 *  @date      2018/11/21 23:20
 *  @version   1.0
 */
@Service
@AllArgsConstructor
public class GeneratorUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public SecurityUser loadUserByUsername(String phone) throws UsernameNotFoundException {
        SecurityUser securityUser = userMapper.findByPhone(phone);

        if (Objects.isNull(securityUser)) {
            throw new UsernameNotFoundException(phone);
        }

        Collection<? extends GrantedAuthority> authorities = Stream.of(securityUser.getRole().getName())
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        securityUser.setAuthorities(authorities);

        return securityUser;
    }
}
