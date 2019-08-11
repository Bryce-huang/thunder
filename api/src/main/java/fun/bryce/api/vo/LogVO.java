

package fun.bryce.api.vo;

import fun.bryce.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bryce
 * @date 2019/07/1
 */
@Data
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private SysLog sysLog;
	private String username;
}
