package isa.qa.project.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import isa.qa.project.core.Result;
import isa.qa.project.core.ResultGenerator;
import isa.qa.project.dto.UserDTO;
import isa.qa.project.dto.UserLoginDTO;
import isa.qa.project.dto.UserRegisterDTO;
import isa.qa.project.security.SecurityUser;
import isa.qa.project.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(tags = "用户(账户)", description = "用户(账户)增删改查")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserApi {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    private final UserService userService;

    @ApiOperation(value = "登录", notes = "账户登录")
    @PostMapping("/actions/login")
    public Result<SecurityUser> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        LOGGER.info("API 调用 ： 用户账户登录");

        return ResultGenerator.genSuccessResult(userService.login(loginDTO));
    }

    @ApiOperation(value = "登出", notes = "账户退出登录")
    @GetMapping("/actions/logout")
    public Result<Map<String, Boolean>> logout() {
        LOGGER.info("API 调用 ： 用户账户登出");

        return ResultGenerator.genSuccessResult(userService.logout());
    }

    @ApiOperation(value = "用户注册", notes = "新用户注册")
    @PostMapping("/actions/register")
    public Result<Map<String, Long>> registerUser(@Valid @RequestBody UserRegisterDTO registerRequestDTO) {
        LOGGER.info("API 调用 ：新用户注册");

        return ResultGenerator.genSuccessResult(userService.registerUser(registerRequestDTO));
    }

    @ApiOperation(value = "用户更新", notes = "用户信息更新")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path", required = true)
    @PutMapping("/{id}")
    public Result<Map<String, Boolean>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        LOGGER.info("API 调用 ： 用户信息更新");

        return ResultGenerator.genSuccessResult(userService.updateUser(id, userDTO));
    }

    @ApiOperation(value = "手机号占用检查", notes = "检查手机号是否被占用注册")
    @ApiImplicitParam(name = "phoneNumber", value = "手机号", paramType = "query", required = true)
    @GetMapping("/phoneNums/actions/check")
    public Result<Map<String, Boolean>> checkPhoneNumber(@RequestParam String phoneNumber) {
        LOGGER.info("API 调用 ： 检查手机号是否被占用注册");

        return ResultGenerator.genSuccessResult(userService.checkPhoneNumber(phoneNumber));
    }

    @ApiOperation(value = "验证码发送", notes = "发送手机注册验证码")
    @ApiImplicitParam(name = "phoneNumber", value = "手机号", paramType = "query")
    @GetMapping("/verifyCodes/actions/send")
    public Result<Map<String, Boolean>> sendVerifyCode(@RequestParam String phoneNumber) {
        LOGGER.info("API 调用 ：发送验证码短信");

        return ResultGenerator.genSuccessResult(userService.sendVerifyCode(phoneNumber));
    }
}
