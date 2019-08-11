

package fun.bryce.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fun.bryce.core.constant.CommonConstants;
import fun.bryce.security.exception.SecurityAuth2Exception;
import lombok.SneakyThrows;

/**
 * @author bryce
 * @date 2019/07/1
 * <p>
 * OAuth2 异常格式化
 */
public class SecurityAuth2ExceptionSerializer extends StdSerializer<SecurityAuth2Exception> {
	public SecurityAuth2ExceptionSerializer() {
		super(SecurityAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(SecurityAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
