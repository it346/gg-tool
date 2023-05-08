package io.github.it346.boot.config;

import io.github.it346.launch.props.Properties;
import io.github.it346.tool.constant.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 *
 * @author wg
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties({
	Properties.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@AllArgsConstructor
public class BootAutoConfiguration {

	private Properties properties;

	/**
	 * 全局变量定义
	 *
	 * @return SystemConstant
	 */
	@Bean
	public SystemConstant fileConst() {
		SystemConstant me = SystemConstant.me();

		//设定开发模式
		me.setDevMode(("dev".equals(properties.getEnv())));

		//设定文件上传远程地址
		me.setDomain(properties.get("upload-domain", "http://localhost:8888"));

		//设定文件上传是否为远程模式
		me.setRemoteMode(properties.getBoolean("remote-mode", true));

		//远程上传地址
		me.setRemotePath(properties.get("remote-path", System.getProperty("user.dir") + "/work/gg"));

		//设定文件上传头文件夹
		me.setUploadPath(properties.get("upload-path", "/upload"));

		//设定文件下载头文件夹
		me.setDownloadPath(properties.get("download-path", "/download"));

		//设定上传图片是否压缩
		me.setCompress(properties.getBoolean("compress", false));

		//设定上传图片压缩比例
		me.setCompressScale(properties.getDouble("compress-scale", 2.00));

		//设定上传图片缩放选择:true放大;false缩小
		me.setCompressFlag(properties.getBoolean("compress-flag", false));

		return me;
	}

}
