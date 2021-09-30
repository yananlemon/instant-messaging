package site.ilemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;
import site.ilemon.websocket.bootstrap.WebSocketServer;

@SpringBootApplication
@Slf4j
public class InstantMessagingApplication implements ApplicationRunner {

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch("Spring Boot Application");
        stopWatch.start("application");
        SpringApplication.run(InstantMessagingApplication.class, args);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }


    /**
     * 启动webSocket服务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread thread = new Thread(() -> {
            WebSocketServer.INSTANCE.start();
        });
        thread.start();
    }

}
