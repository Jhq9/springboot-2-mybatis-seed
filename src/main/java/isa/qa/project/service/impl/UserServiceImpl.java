package isa.qa.project.service.impl;

import com.github.wujun234.uid.UidGenerator;
import isa.qa.project.dto.UserDTO;
import isa.qa.project.dto.UserLoginDTO;
import isa.qa.project.dto.UserRegisterDTO;
import isa.qa.project.exception.ServiceException;
import isa.qa.project.mapper.RoleMapper;
import isa.qa.project.mapper.UserMapper;
import isa.qa.project.model.Role;
import isa.qa.project.model.User;
import isa.qa.project.security.GeneratorUserDetailService;
import isa.qa.project.security.SecurityUser;
import isa.qa.project.security.SecurityUtils;
import isa.qa.project.service.UserService;
import isa.qa.project.utils.ResultMapUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static isa.qa.project.constants.CacheConstants.KEY_PREFIX_VERIFY_CODE;
import static isa.qa.project.utils.ArgumentCheckUtils.checkEquals;
import static isa.qa.project.utils.ArgumentCheckUtils.checkNonNull;
import static isa.qa.project.utils.ResultMapUtils.genIdResultMap;
import static isa.qa.project.utils.ResultMapUtils.genUpdateResultMap;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * User service implement
 *
 * @author May
 * @version 1.0
 * @date 2018/11/22 9:48
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final UidGenerator cachedUidGenerator;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final GeneratorUserDetailService userDetailService;

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public SecurityUser login(UserLoginDTO loginDTO) {
        //根据用户输入的账号及密码生成AuthenticationToken
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getPhoneNumber(), loginDTO.getPassword());
        try {
            //用户名密码登陆效验
            final Authentication authentication = authenticationManager.authenticate(upToken);
            //认证成功，将认证信息存入holder中
            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
            SecurityContextHolder.setContext(ctx);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return (SecurityUser) SecurityUtils.getUserDetails();
        } catch (AuthenticationException e) {
            throw new ServiceException("用户不存在或密码错误");
        }
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Map<String, Long> registerUser(UserRegisterDTO registerDTO) {
        Role role = roleMapper.selectByPrimaryKey(registerDTO.getRoleId());
        checkNonNull(role, "未找到对应的角色");

        String cachedVerifyCode = stringRedisTemplate.opsForValue().get(KEY_PREFIX_VERIFY_CODE + registerDTO.getPhoneNumber());
        checkEquals(registerDTO.getVerifyCode(), cachedVerifyCode, "验证码错误或已失效");

        LocalDateTime now = LocalDateTime.now();
        User user = new User();

        user.setId(cachedUidGenerator.getUID());
        user.setRoleId(registerDTO.getRoleId());
        user.setUsername(registerDTO.getPhoneNumber());
        user.setNickname(registerDTO.getUsername());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setIsEnabled(TRUE);
        user.setEmail(registerDTO.getEmail());
        user.setLastPasswordResetTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        user.setCreateTime(now);
        user.setUpdateTime(now);

        userMapper.insert(user);

        return genIdResultMap("userId", user.getId());
    }

    @Override
    public Map<String, Boolean> logout() {
        SecurityContextHolder.clearContext();

        return genUpdateResultMap("isSuccess", TRUE);
    }

    @Override
    public Map<String, Boolean> checkPhoneNumber(String phoneNumber) {
        int userCount = countUserByPhoneNumber(phoneNumber);

        return genUpdateResultMap("isRegistered", userCount == 1);
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Map<String, Boolean> updateUser(Long id, UserDTO userDTO) {
        User user = userMapper.selectByPrimaryKey(id);
        checkNonNull(user, "未找到对应的用户");

        if (isNotEmpty(userDTO.getUsername())) {
            user.setNickname(userDTO.getUsername());
        }
        if (isNotEmpty(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }

        user.setUpdateTime(LocalDateTime.now());

        int updatedNum = userMapper.updateByPrimaryKeySelective(user);

        return ResultMapUtils.genUpdateResultMap("isSuccess", updatedNum == 1);
    }

    @Override
    public Map<String, Boolean> sendVerifyCode(String phoneNumber) {
        String verifyCode = randomNumeric(6);
        LOGGER.debug("Phone={}, VerifyCode={}", phoneNumber, verifyCode);

        String key = KEY_PREFIX_VERIFY_CODE + phoneNumber;
        stringRedisTemplate.opsForValue().set(key, verifyCode);

        stringRedisTemplate.expire(key, 180, TimeUnit.SECONDS);

        return genUpdateResultMap(key, TRUE);
    }

    /**
     * Count the user by registered phone
     *
     * @param phoneNumber phone number
     * @return user count
     */
    private int countUserByPhoneNumber(String phoneNumber) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phoneNumber", phoneNumber);

        return userMapper.selectCountByExample(example);
    }
}

