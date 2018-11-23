package isa.qa.project.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *  角色
 *
 *  @author    May
 *  @date      2018/11/21 16:31
 *  @version   1.0
 */
@Data
@Table(name = "i_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Role's name
     */
    @Column(name = "name")
    private String name;

    /**
     * Role created time
     */
    @Column(name = "created_time")
    private Date createdTime;
}
