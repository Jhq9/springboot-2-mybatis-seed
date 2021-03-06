package isa.qa.project.mapper;

import isa.qa.project.base.BaseMapper;
import isa.qa.project.model.User;
import isa.qa.project.vo.UserVO;
import org.apache.ibatis.annotations.*;

/**
 *  User Mapper
 *
 *  @author    May
 *  @date      2018/11/21 16:41
 *  @version   1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * Get the account and role info by the unique phone number
     *
     * @param username username
     * @return account and role info
     */
    @Select("SELECT id,role_id roleId,username,nickname,phone_number phoneNumber,email,password,is_enabled isEnabled,"
            + "create_time createTime,update_time updateTime,last_password_reset_time lastPasswordResetTime "
            + "FROM i_user WHERE username=#{username}")
    @Results(value = {
            @Result(property = "role", column = "roleId",
                    one = @One(select = "isa.qa.project.mapper.RoleMapper.findById"))
    })
    UserVO findByUsername(@Param("username") String username);
}
