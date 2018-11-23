package isa.qa.project.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import isa.qa.project.core.Result;
import isa.qa.project.core.ResultGenerator;
import isa.qa.project.dto.request.UserLoginRequestDTO;
import isa.qa.project.dto.request.UserRegisterRequestDTO;
import isa.qa.project.dto.request.UserRequestDTO;
import isa.qa.project.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
    public Result login(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO) {
        LOGGER.info("API 调用 ： 用户账户登录");

        return ResultGenerator.genSuccessResult(userService.login(loginRequestDTO));
    }

    @ApiOperation(value = "登出", notes = "账户退出登录")
    @GetMapping("/actions/logout")
    public Result logout(@AuthenticationPrincipal Principal principal) {
        LOGGER.info("API 调用 ： 用户账户登出");

        return ResultGenerator.genSuccessResult(userService.logout(principal));
    }

    @ApiOperation(value = "用户注册", notes = "新用户注册")
    @PostMapping("/actions/register")
    public Result registerUser(@Valid @RequestBody UserRegisterRequestDTO registerRequestDTO) {
        LOGGER.info("API 调用 ：新用户注册");

        return ResultGenerator.genSuccessResult(userService.registerUser(registerRequestDTO));
    }

    @ApiOperation(value = "用户更新", notes = "用户信息更新")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path")
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        LOGGER.info("API 调用 ： 用户信息更新");

        return ResultGenerator.genSuccessResult(userService.updateUser(id, userRequestDTO));
    }

    @ApiOperation(value = "手机号占用检查", notes = "检查手机号是否被占用注册")
    @ApiImplicitParam(name = "phone", value = "手机号", paramType = "query")
    @GetMapping("/phone_num/actions/check")
    public Result checkPhone(@RequestParam String phone) {
        LOGGER.info("API 调用 ： 检查手机号是否被占用注册");

        return ResultGenerator.genSuccessResult(userService.checkPhone(phone));
    }

    @ApiOperation(value = "验证码发送", notes = "发送手机注册验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", paramType = "query")
    @GetMapping("/verify_codes/actions/send")
    public Result sendVerifyCode(@RequestParam String phone) {
        LOGGER.info("API 调用 ：发送验证码短信");

        return ResultGenerator.genSuccessResult(userService.sendVerifyCode(phone));
    }
}
