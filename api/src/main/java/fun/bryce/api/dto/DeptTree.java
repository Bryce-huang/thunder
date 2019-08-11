

package fun.bryce.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bryce
 * @date 2019/07/1
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
	private String name;
}
