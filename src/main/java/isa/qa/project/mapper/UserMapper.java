package isa.qa.project.mapper;

import isa.qa.project.core.MyMapper;
import isa.qa.project.model.User;
import isa.qa.project.security.SecurityUser;
import org.apache.ibatis.annotations.*;

/**
 *  User Mapper
 *
 *  @author    May
 *  @date      2018/11/21 16:41
 *  @version   1.0
 */
@Mapper
public interface UserMapper extends MyMapper<User> {

    /**
     * Get the account and role info by the unique phone number
     *
     * @param phone unique user's phone number
     * @return account and role info
     */
    @Select("SELECT id,role_id roleId,name,phone,email,password,is_enabled,"
            + "register_time,updated_time,last_password_reset_time "
            + "FROM i_user WHERE phone=#{phone}")
    @Results(value = {
            @Result(property = "roleId", column = "roleId", javaType = Long.class),
            @Result(property = "role", column = "roleId",
                    one = @One(select = "isa.qa.project.mapper.RoleMapper.selectByPrimaryKey"))
    })
    SecurityUser findByPhone(@Param("phone") String phone);
}
