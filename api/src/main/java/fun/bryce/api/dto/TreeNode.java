

package fun.bryce.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bryce
 * @date 2019年07月22日23:33:45
 */
@Data
public class TreeNode {
	protected int id;
	protected int parentId;
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public void add(TreeNode node) {
		children.add(node);
	}
}
