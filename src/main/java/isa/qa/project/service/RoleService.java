package isa.qa.project.service;

import isa.qa.project.dto.request.RoleRequestDTO;
import isa.qa.project.model.Role;

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
    List<Role> listRole();

    /**
     * Add a new role
     *
     * @param roleRequestDTO role request data
     * @return request result map
     */
    Map<String, Long> saveRole(RoleRequestDTO roleRequestDTO);

    /**
     * Update the saved role info
     *
     * @param id role id
     * @param roleRequestDTO updating role info
     * @return request result map
     */
    Map<String, Boolean> updateRole(Long id, RoleRequestDTO roleRequestDTO);

    /**
     * Remove the role
     *
     * @param id role id
     * @return request result map
     */
    Map<String, Boolean> removeRole(Long id);
}
