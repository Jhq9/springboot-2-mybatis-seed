package isa.qa.project.service;

import isa.qa.project.dto.UserDTO;
import isa.qa.project.dto.UserLoginDTO;
import isa.qa.project.dto.UserRegisterDTO;
import isa.qa.project.security.SecurityUser;

import java.security.Principal;
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
     * User login (session)
     *
     * @param loginDTO account login info
     * @return user info
     */
    SecurityUser login(UserLoginDTO loginDTO);

    /**
     * Register a new user
     *
     * @param registerDTO account registerUser info
     * @return result map
     */
    Map<String, Long> registerUser(UserRegisterDTO registerDTO);

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
     * @param userDTO account info
     * @return result map
     */
    Map<String, Boolean> updateUser(Long id, UserDTO userDTO);

    /**
     * Send verify code to the phone
     *
     * @param phone phone number
     * @return send result
     */
    Map<String, Boolean> sendVerifyCode(String phone);
}
