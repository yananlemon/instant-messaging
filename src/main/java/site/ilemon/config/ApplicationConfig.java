package site.ilemon.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Component
@Data
public class ApplicationConfig {

    @Autowired
    private Environment env;
    /**
     * netty服务端口号
     */
    public static int NETTY_WS_SERVER_PORT;

    /**
     * 初始化系统参数
     */
    public void init() {
        NETTY_WS_SERVER_PORT = env.getProperty("app.netty.port", Integer.class);
    }
}
