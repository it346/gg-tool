package io.github.it346.secure.props;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户端令牌认证信息
 *
 * @author wg
 */
@Data
public class ClientSecure {

	private String clientId;

	private final List<String> pathPatterns = new ArrayList<>();

}
