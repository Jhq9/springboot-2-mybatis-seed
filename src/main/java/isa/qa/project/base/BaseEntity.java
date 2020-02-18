package isa.qa.project.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;

/**
 * Base Entity
 *
 * @author May
 * @version 1.0
 * @date 2019/12/18 12:02
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Entity id
	 */
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * create time
	 */
	@Column(name = "create_time")
	private LocalDateTime createTime;

	/**
	 * update time
	 */
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	/**
	 * is deleted
	 */
	@Column(name = "is_deleted")
	private Boolean isDeleted = FALSE;
}
