package fun.bryce.core.exception;

/**
 * @author bryce
 * @date 2019年07月22日16:22:15
 */
public class ValidateCodeException extends Exception {
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException() {
	}

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
