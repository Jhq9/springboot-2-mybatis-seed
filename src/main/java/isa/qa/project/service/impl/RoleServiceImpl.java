package isa.qa.project.service.impl;

import com.github.wujun234.uid.UidGenerator;
import isa.qa.project.dto.RoleDTO;
import isa.qa.project.mapper.RoleMapper;
import isa.qa.project.model.Role;
import isa.qa.project.service.RoleService;
import isa.qa.project.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static isa.qa.project.utils.ArgumentCheckUtils.checkNonNull;
import static isa.qa.project.utils.ResultMapUtils.genIdResultMap;
import static isa.qa.project.utils.ResultMapUtils.genUpdateResultMap;

/**
 * Role service implement
 *
 * @author May
 * @version 1.0
 * @date 2018/11/22 9:17
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UidGenerator cachedUidGenerator;

    private final RoleMapper roleMapper;

    @Override
    public List<RoleVO> listRole() {
        return roleMapper.findAll();
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Map<String, Long> saveRole(RoleDTO roleDTO) {
        Role role = new Role();

        role.setId(cachedUidGenerator.getUID());
        role.setName(roleDTO.getName());
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(role.getCreateTime());

        roleMapper.insert(role);

        return genIdResultMap("roleId", role.getId());
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Map<String, Boolean> updateRole(Long id, RoleDTO roleDTO) {
        Role role = roleMapper.selectByPrimaryKey(id);
        checkNonNull(role, "未找到对应的角色");

        role.setName(roleDTO.getName());
        int updatedNum = roleMapper.updateByPrimaryKeySelective(role);

        return genUpdateResultMap("isSuccess", updatedNum == 1);
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public Map<String, Boolean> removeRole(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        checkNonNull(role, "未找到对应的角色");
        //TODO 校验拥有该角色的用户是否存在，存在则不能删除

        int deletedNum = roleMapper.deleteByPrimaryKey(id);

        return genUpdateResultMap("isSuccess", deletedNum == 1);
    }
}
