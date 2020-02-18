package isa.qa.project.model;

import isa.qa.project.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *  角色
 *
 *  @author    May
 *  @date      2018/11/21 16:31
 *  @version   1.0
 */
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "i_role")
public class Role extends BaseEntity {

    /**
     * Role's name
     */
    @Column(name = "name")
    private String name;
}
