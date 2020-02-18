package isa.qa.project.service;

import isa.qa.project.dto.RoleDTO;
import isa.qa.project.vo.RoleVO;

import java.util.List;
import java.util.Map;

/**
 *  Role Service
 *
 *  @author    May
 *  @date      2018/11/21 16:42
 *  @version   1.0
 */
public interface RoleService {

    /**
     * List all role
     *
     * @return role list
     */
    List<RoleVO> listRole();

    /**
     * Add a new role
     *
     * @param roleDTO role request data
     * @return request result map
     */
    Map<String, Long> saveRole(RoleDTO roleDTO);

    /**
     * Update the saved role info
     *
     * @param id role id
     * @param roleDTO updating role info
     * @return request result map
     */
    Map<String, Boolean> updateRole(Long id, RoleDTO roleDTO);

    /**
     * Remove the role
     *
     * @param id role id
     * @return request result map
     */
    Map<String, Boolean> removeRole(Long id);
}
