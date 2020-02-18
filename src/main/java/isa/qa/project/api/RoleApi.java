package isa.qa.project.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import isa.qa.project.core.Result;
import isa.qa.project.core.ResultGenerator;
import isa.qa.project.dto.RoleDTO;
import isa.qa.project.service.RoleService;
import isa.qa.project.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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
    public Result<RoleVO> listRole() {
        LOGGER.info("API 调用 ： 获取所有的角色的列表");

        return ResultGenerator.genSuccessResult(roleService.listRole());
    }

    @ApiOperation(value = "保存角色", notes = "新增一个角色")
    @PostMapping("")
    public Result<Map<String, Long>> saveRole(@Valid @RequestBody RoleDTO roleDTO) {
        LOGGER.info("API 调用 ： 新增一个角色");

        return ResultGenerator.genSuccessResult(roleService.saveRole(roleDTO));
    }

    @ApiOperation(value = "更新角色", notes = "更新角色信息")
    @ApiImplicitParam(name = "id", value = "角色id", paramType = "path")
    @PutMapping("/{id}")
    public Result<Map<String, Boolean>> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        LOGGER.info("API 调用 ： 更新角色信息");

        return ResultGenerator.genSuccessResult(roleService.updateRole(id, roleDTO));
    }
}
