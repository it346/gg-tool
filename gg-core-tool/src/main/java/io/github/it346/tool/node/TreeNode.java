package io.github.it346.tool.node;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树型节点类
 *
 * @author wg
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseNode {

	private static final long serialVersionUID = 1L;

	private String title;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long key;

	@JsonSerialize(using = ToStringSerializer.class)
	private Long value;

}