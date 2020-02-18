package isa.qa.project.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色 VO
 *
 * @author May
 * @version 1.0
 * @date 2019/12/22 12:06
 */
@Data
public class RoleVO {

	/**
	 * 角色的ID
	 */
	@ApiModelProperty(value = "角色的ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	 * 角色的名字
	 */
	@ApiModelProperty(value = "角色的名字")
	private String name;
}
