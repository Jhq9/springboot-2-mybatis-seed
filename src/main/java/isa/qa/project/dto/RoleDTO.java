package isa.qa.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 *  Role transport object
 *
 *  @author    May
 *  @date      2018/11/22 8:51
 *  @version   1.0
 */
@Data
public class RoleDTO {

    /**
     * Role name
     */
    @ApiModelProperty(value = "角色的名称")
    @NotEmpty(message = "角色的名称不能为空")
    private String name;
}

