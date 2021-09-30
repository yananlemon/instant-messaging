package site.ilemon.websocket.cache;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
public class ChannelCache {

    private static final ChannelCache INSTANCE = new ChannelCache();

    private ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static ChannelCache getInstance() {
        return INSTANCE;
    }
    public Channel get(String username) {
        if (username == null) {
            return null;
        }
        return channelMap.get(username);
    }

    public void put(String username, Channel session) {
        channelMap.put(username, session);
    }

    public void remove(String sessionId) {
        /*if (StringUtils.isEmpty(sessionId)) {
            return;
        }
        Iterator<String> iterator = channelMap.keySet().iterator();
        while (iterator.hasNext()) {
            String username = iterator.next();
            Channel channel = channelMap.get(username);
            if (sessionId.equals(channel.get())) {
                iterator.remove();
            }
        }*/
    }

    public Map<String, Channel> list() {
        return channelMap;
    }
}
