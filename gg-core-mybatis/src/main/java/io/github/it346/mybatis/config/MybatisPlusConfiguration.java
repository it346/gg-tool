package io.github.it346.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import io.github.it346.mybatis.intercept.QueryInterceptor;
import io.github.it346.mybatis.plugins.PaginationInterceptor;
import io.github.it346.mybatis.plugins.SqlLogInterceptor;
import io.github.it346.mybatis.props.MybatisPlusProperties;
import io.github.it346.secure.utils.SecureUtil;
import io.github.it346.tool.constant.Constant;
import io.github.it346.tool.utils.Func;
import io.github.it346.tool.utils.ObjectUtil;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

/**
 * mybatis-plus 配置
 *
 * @author wg
 */
@AutoConfiguration
@AllArgsConstructor
@MapperScan("io.github.it346.**.mapper.**")
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class MybatisPlusConfiguration {


	/**
	 * 租户拦截器
	 */
	@Bean
	@ConditionalOnMissingBean(TenantLineInnerInterceptor.class)
	public TenantLineInnerInterceptor tenantLineInnerInterceptor() {
		return new TenantLineInnerInterceptor(new TenantLineHandler() {
			@Override
			public Expression getTenantId() {
				return new StringValue(Func.toStr(SecureUtil.getTenantId(), Constant.ADMIN_TENANT_ID));
			}

			@Override
			public boolean ignoreTable(String tableName) {
				return true;
			}
		});
	}

	/**
	 * mybatis-plus 拦截器集合
	 */
	@Bean
	@ConditionalOnMissingBean(MybatisPlusInterceptor.class)
	public MybatisPlusInterceptor mybatisPlusInterceptor(ObjectProvider<QueryInterceptor[]> queryInterceptors,
														 TenantLineInnerInterceptor tenantLineInnerInterceptor,
														 MybatisPlusProperties mybatisPlusProperties) {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 配置租户拦截器
		interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
		// 配置分页拦截器
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		// 配置自定义查询拦截器
		QueryInterceptor[] queryInterceptorArray = queryInterceptors.getIfAvailable();
		if (ObjectUtil.isNotEmpty(queryInterceptorArray)) {
			AnnotationAwareOrderComparator.sort(queryInterceptorArray);
			paginationInterceptor.setQueryInterceptors(queryInterceptorArray);
		}
		paginationInterceptor.setMaxLimit(mybatisPlusProperties.getPageLimit());
		paginationInterceptor.setOverflow(mybatisPlusProperties.getOverflow());
		interceptor.addInnerInterceptor(paginationInterceptor);
		return interceptor;
	}

	/**
	 * sql 日志
	 *
	 * @return SqlLogInterceptor
	 */
	@Bean
	@ConditionalOnProperty(value = "gg.mybatis-plus.sql-log", matchIfMissing = true)
	public SqlLogInterceptor sqlLogInterceptor() {
		return new SqlLogInterceptor();
	}

}

