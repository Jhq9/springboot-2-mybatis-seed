package isa.qa.project.service;

import isa.qa.project.dto.request.UserLoginRequestDTO;
import isa.qa.project.dto.request.UserRegisterRequestDTO;
import isa.qa.project.dto.request.UserRequestDTO;
import isa.qa.project.model.User;
import isa.qa.project.security.SecurityUser;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *  User Service
 *
 *  @author    May
 *  @date      2018/11/21 16:42
 *  @version   1.0
 */
public interface UserService {

    /**
     * List user
     *
     * @return user list
     */
    List<User> listUser();

    /**
     * User login (session)
     *
     * @param loginRequestDTO account login info
     * @return user info
     */
    SecurityUser login(UserLoginRequestDTO loginRequestDTO);

    /**
     * Register a new user
     *
     * @param registerRequestDTO account registerUser info
     * @return result map
     */
    Map<String, Long> registerUser(UserRegisterRequestDTO registerRequestDTO);

    /**
     * Account log out
     *
     * @return log out result
     * @param principal
     */
    Map<String, Boolean> logout(Principal principal);

    /**
     * Check the phone whether registered
     *
     * @param phone phone number
     * @return result map
     */
    Map<String, Boolean> checkPhone(String phone);

    /**
     * Update the user account info
     *
     * @param id user id
     * @param userRequestDTO account info
     * @return result map
     */
    Map<String, Boolean> updateUser(Long id, UserRequestDTO userRequestDTO);

    /**
     * Send verify code to the phone
     *
     * @param phone phone number
     * @return send result
     */
    Map<String, Boolean> sendVerifyCode(String phone);
}
