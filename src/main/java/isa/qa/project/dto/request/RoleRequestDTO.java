package isa.qa.project.dto.request;

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
public class RoleRequestDTO {

    /**
     * Role name
     */
    @NotEmpty(message = "角色的名称不能为空")
    private String name;
}

