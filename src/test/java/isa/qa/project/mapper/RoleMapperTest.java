package isa.qa.project.mapper;

import isa.qa.project.ApplicationTests;
import isa.qa.project.model.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;

public class RoleMapperTest extends ApplicationTests {

    @Autowired
    private RoleMapper roleMapper;

    private Long roleId;

    @Before
    public void init() {
        Role role = new Role();

        role.setName(Mockito.anyString());
        role.setCreatedTime(Mockito.any(Date.class));

        roleMapper.insertUseGeneratedKeys(role);

        Assert.notNull(roleId = role.getId(), "Save the new role failed");
    }

    @Test
    public void findById() {
        Role role = roleMapper.selectByPrimaryKey(roleId);

        Assert.notNull(role, "The role not found");
    }

}
