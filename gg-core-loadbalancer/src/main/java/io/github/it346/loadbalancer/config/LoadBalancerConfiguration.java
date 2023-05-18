package io.github.it346.loadbalancer.config;

import io.github.it346.loadbalancer.props.LoadBalancerProperties;
import io.github.it346.loadbalancer.rule.GrayscaleLoadBalancer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

/**
 * blade 负载均衡策略
 *
 * @author wg
 */
@AutoConfiguration
@AutoConfigureBefore(LoadBalancerClientConfiguration.class)
@EnableConfigurationProperties(LoadBalancerProperties.class)
@ConditionalOnProperty(value = LoadBalancerProperties.PROPERTIES_PREFIX + ".enabled", matchIfMissing = true)
@Order(LoadBalancerConfiguration.REACTIVE_SERVICE_INSTANCE_SUPPLIER_ORDER)
public class LoadBalancerConfiguration {
	public static final int REACTIVE_SERVICE_INSTANCE_SUPPLIER_ORDER = 193827465;

	@Bean
	public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
																				   LoadBalancerClientFactory loadBalancerClientFactory,
																				   LoadBalancerProperties loadBalancerProperties) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new GrayscaleLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), loadBalancerProperties);
	}

	@Bean
	public LoadBalancerClientSpecification loadBalancerClientSpecification() {
		return new LoadBalancerClientSpecification("default.loadBalancerConfiguration",
			new Class[]{LoadBalancerConfiguration.class});
	}

}
