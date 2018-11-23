package isa.qa.project.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import isa.qa.project.core.Result;
import isa.qa.project.core.ResultGenerator;
import isa.qa.project.dto.request.RoleRequestDTO;
import isa.qa.project.security.SecurityUser;
import isa.qa.project.service.RoleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Api(tags = "角色", description = "角色增删改查")
@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleApi {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleApi.class);

    private final RoleService roleService;

    @ApiOperation(value = "角色列表", notes = "获取所有的角色的列表")
    @GetMapping("/actions/list")
    public Result listRole() {
        LOGGER.info("API 调用 ： 获取所有的角色的列表");

        return ResultGenerator.genSuccessResult(roleService.listRole());
    }

    @ApiOperation(value = "保存角色", notes = "新增一个角色")
    @PostMapping("")
    public Result saveRole(@Valid @RequestBody RoleRequestDTO roleRequestDTO) {
        LOGGER.info("API 调用 ： 新增一个角色");

        return ResultGenerator.genSuccessResult(roleService.saveRole(roleRequestDTO));
    }

    @ApiOperation(value = "更新角色", notes = "更新角色信息")
    @ApiImplicitParam(name = "id", value = "角色id", paramType = "path")
    @PutMapping("/{id}")
    public Result updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO roleRequestDTO) {
        LOGGER.info("API 调用 ： 更新角色信息");

        return ResultGenerator.genSuccessResult(roleService.updateRole(id, roleRequestDTO));
    }
}
