

package fun.bryce.log.annotation;

import java.lang.annotation.*;

/**
 * @author bryce
 * @date 2019/07/1
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 *
	 * @return {String}
	 */
	String value();
}
