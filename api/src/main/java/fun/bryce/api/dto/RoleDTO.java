

package fun.bryce.api.dto;

import fun.bryce.api.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bryce
 * @date 2019/07/1
 * 角色Dto
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {
	/**
	 * 角色部门Id
	 */
	private Integer roleDeptId;

	/**
	 * 部门名称
	 */
	private String deptName;
}
