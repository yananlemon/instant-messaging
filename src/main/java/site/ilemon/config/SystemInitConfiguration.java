package site.ilemon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SystemInitConfiguration implements CommandLineRunner, EnvironmentAware {

    @Autowired
    private ApplicationConfig applicationConfig;


    /**
     * @param environment 配置信息
     * @Bean实例化之前执行 用于加载配置信息
     */
    @Override
    public void setEnvironment(Environment environment) {
        long now = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("SystemInitConfig setEnvironment ....");
        }
        // 初始化项目配置信息
        applicationConfig.init();

        if (log.isDebugEnabled()) {
            log.debug("SystemInitConfig setEnvironment execute done, time consuming：{} milliseconds!", System.currentTimeMillis() - now);
        }

    }

    /**
     * 在服务启动后执行，会在@Bean实例化之后执行
     *
     * @param strings 参数
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {

    }
}
