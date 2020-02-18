package isa.qa.project.security;

import isa.qa.project.mapper.UserMapper;
import isa.qa.project.vo.UserVO;
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
 * Get user detail of security service
 *
 * @author May
 * @version 1.0
 * @date 2018/11/21 23:20
 */
@Service
@AllArgsConstructor
public class GeneratorUserDetailService implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userMapper.findByUsername(username);

		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException(username);
		}

		return createSecurityUser(user);
	}

	private UserDetails createSecurityUser(UserVO user) {
		return new SecurityUser(user.getId(), user.getUsername(), user.getNickname(), user.getPassword(),
				user.getPhoneNumber(), user.getEmail(), user.isEnabled(), user.getLastPasswordResetTime(),
				user.getCreateTime(), user.getUpdateTime(), user.getRole(),
				Stream.of(user.getRole().getName())
					  .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}
}
