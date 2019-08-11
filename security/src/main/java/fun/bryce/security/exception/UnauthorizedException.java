

package fun.bryce.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fun.bryce.security.component.SecurityAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author bryce
 * @date 2019/07/1
 */
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class UnauthorizedException extends SecurityAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
