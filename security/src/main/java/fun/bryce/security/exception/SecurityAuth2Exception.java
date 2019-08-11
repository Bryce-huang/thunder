

package fun.bryce.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fun.bryce.security.component.SecurityAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author bryce
 * @date 2019/07/1
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = SecurityAuth2ExceptionSerializer.class)
public class SecurityAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public SecurityAuth2Exception(String msg) {
		super(msg);
	}

	public SecurityAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
