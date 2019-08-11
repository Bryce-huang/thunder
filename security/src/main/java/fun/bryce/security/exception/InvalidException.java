

package fun.bryce.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fun.bryce.security.component.SecurityAuth2ExceptionSerializer;

/**
 * @author bryce
 * @date 2019/07/1
 */
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class InvalidException extends SecurityAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
