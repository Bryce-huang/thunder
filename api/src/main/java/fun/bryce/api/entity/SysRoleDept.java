

package fun.bryce.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色与部门对应关系
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_dept")
public class SysRoleDept extends Model<SysRoleDept> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 部门ID
	 */
	private Integer deptId;

}
