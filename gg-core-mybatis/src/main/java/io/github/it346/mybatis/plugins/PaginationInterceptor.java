package io.github.it346.mybatis.plugins;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import io.github.it346.mybatis.intercept.QueryInterceptor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 拓展分页拦截器
 *
 * @author wg
 */
@Setter
public class PaginationInterceptor extends PaginationInnerInterceptor {

	/**
	 * 查询拦截器
	 */
	private QueryInterceptor[] queryInterceptors;

	@SneakyThrows
	@Override
	public boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
		QueryInterceptorExecutor.exec(queryInterceptors, executor, ms, parameter, rowBounds, resultHandler, boundSql);
		return super.willDoQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
	}

}
