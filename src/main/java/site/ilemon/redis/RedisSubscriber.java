package site.ilemon.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Component
public class RedisSubscriber implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {

    }
}
