package io.github.it346.tool.node;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 森林节点类
 *
 * @author wg
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ForestNode extends BaseNode {

	private static final long serialVersionUID = 1L;

	/**
	 * 节点内容
	 */
	private Object content;

	public ForestNode(Long id, Long parentId, Object content) {
		this.id = id;
		this.parentId = parentId;
		this.content = content;
	}

}
