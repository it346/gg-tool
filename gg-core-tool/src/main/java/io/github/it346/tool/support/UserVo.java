package io.github.it346.tool.support;

import lombok.Data;

import java.util.Date;

/**
 * @author wg
 * @date 2023-05-02
 */
@Data
public class UserVo {

	private String name;

	private Integer age;

	private Integer sex;

	private Date birth;
}
