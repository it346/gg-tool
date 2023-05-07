/**
 * Copyright (c) 2018-2028, DreamLu 卢春梦 (qq596392912@gmail.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.it346.cloud.version;

import lombok.Getter;

/**
 * blade Media Types，application/vnd.github.VERSION+json
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
