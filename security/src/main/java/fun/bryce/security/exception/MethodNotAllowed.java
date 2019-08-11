

package fun.bryce.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fun.bryce.security.component.SecurityAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author bryce
 * @date 2019/07/1
 */
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends SecurityAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
