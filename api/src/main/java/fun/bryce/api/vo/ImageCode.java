

package fun.bryce.api.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author bryce
 * @date 2019/07/1
 */
@Data
public class ImageCode implements Serializable {
	private String code;

	private LocalDateTime expireTime;

	private BufferedImage image;

	public ImageCode(BufferedImage image, String sRand, int defaultImageExpire) {
		this.image = image;
		this.code = sRand;
		this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);
	}
}
