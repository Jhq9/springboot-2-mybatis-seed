package isa.qa.project.mapper;

import isa.qa.project.base.BaseMapper;
import isa.qa.project.model.Role;
import isa.qa.project.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Role Mapper
 *
 * @author May
 * @version 1.0
 * @date 2018/11/21 16:42
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据角色的ID查询角色信息
	 *
	 * @param id 角色的ID
	 * @return 角色信息
	 */
	@Select("SELECT id,name "
			+ "FROM i_role "
			+ "WHERE id=#{id}")
	RoleVO findById(@Param("id") Long id);

	/**
	 * 获取角色列表
	 *
	 * @return 角色列表
	 */
	@Select("SELECT id,name "
			+ "FROM i_role "
			+ "ORDER BY id ASC")
	List<RoleVO> findAll();
}
