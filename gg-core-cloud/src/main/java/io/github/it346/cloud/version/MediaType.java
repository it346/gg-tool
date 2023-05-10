

package io.github.it346.cloud.version;

import lombok.Getter;

/**
 * gg Media Types，application/vnd.github.VERSION+json
 *
 * <p>
 * <a href="https://developer.github.com/v3/media/">...</a>
 * </p>
 *
 * @author wg
 */
@Getter
public class MediaType {
	private static final String MEDIA_TYPE_TEMP = "application/vnd.%s.%s+json";

	private final String appName = "gg";
	private final String version;
	private final org.springframework.http.MediaType mediaType;

	public MediaType(String version) {
		this.version = version;
		this.mediaType = org.springframework.http.MediaType.valueOf(String.format(MEDIA_TYPE_TEMP, appName, version));
	}

	@Override
	public String toString() {
		return mediaType.toString();
	}
}
