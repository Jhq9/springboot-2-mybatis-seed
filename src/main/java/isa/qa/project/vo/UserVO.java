package isa.qa.project.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import isa.qa.project.converters.LocalDateTimeToLongConverter;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *  用户信息 VO
 *
 *  @author     May
 *  @date      2020/2/18 16:19
 *  @version   1.0
 */
@Data
public class UserVO {

	/**
	 * 用户的ID
	 */
	@ApiModelProperty(value = "用户的ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 用户的账号
	 */
	@ApiModelProperty(value = "用户的账号")
	private String username;

	/**
	 * 用户的姓名
	 */
	@ApiModelProperty(value = "用户的姓名")
	private String nickname;

	/**
	 * 用户的密码
	 */
	@ApiModelProperty(value = "用户的密码")
	@JsonIgnore
	private String password;

	/**
	 * 用户的手机号
	 */
	@ApiModelProperty(value = "用户的手机号")
	private String phoneNumber;

	/**
	 * 用户的邮箱地址
	 */
	@ApiModelProperty(value = "用户的邮箱地址")
	private String email;

	/**
	 * 是否启用
	 */
	@ApiModelProperty(value = "是否启用")
	private boolean isEnabled;

	/**
	 * 上次密码更新时间
	 */
	@ApiModelProperty(value = "上次密码更新时间")
	private Date lastPasswordResetTime;

	/**
	 * 注册时间
	 */
	@ApiModelProperty(value = "注册时间")
	@JsonSerialize(using = LocalDateTimeToLongConverter.class)
	private LocalDateTime createTime;

	/**
	 * 用户信息上次更新时间
	 */
	@ApiModelProperty(value = "用户信息上次更新时间")
	@JsonSerialize(using = LocalDateTimeToLongConverter.class)
	private LocalDateTime updateTime;

	/**
	 * 用户的角色
	 */
	@ApiModelProperty(value = "用户的角色")
	private RoleVO role;
}
