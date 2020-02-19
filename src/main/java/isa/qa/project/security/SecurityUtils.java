package isa.qa.project.security;

import isa.qa.project.exception.ServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前登录的用户
 *
 * @author Zheng Jie
 * @date 2019-01-17
 */
public class SecurityUtils {

	public static UserDetails getUserDetails() {
		UserDetails userDetails;
		try {
			userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new ServiceException("登录状态无效");
		}
		return userDetails;
	}

	/**
	 * 获取系统用户名称
	 *
	 * @return 系统用户名称
	 */
	public static String getUsername() {
		UserDetails userDetails = getUserDetails();
		return userDetails.getUsername();
	}
}
