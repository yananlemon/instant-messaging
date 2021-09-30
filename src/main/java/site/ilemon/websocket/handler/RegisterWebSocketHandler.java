package site.ilemon.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;
import site.ilemon.utils.DateUtils;
import site.ilemon.websocket.cache.ChannelCache;
import site.ilemon.websocket.message.req.RegisterWebSocketReqMessage;
import site.ilemon.websocket.message.resp.OnlineNotificationWebSocketRespMessage;
import site.ilemon.websocket.message.resp.RegisterWebSocketRespMessage;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Component
public class RegisterWebSocketHandler extends BaseHandler<RegisterWebSocketReqMessage> {
    @Override
    public void handle(ChannelHandlerContext ctx, RegisterWebSocketReqMessage msg) {
        Date now = new Date();
        // 将当前用户与channel绑定
        ChannelCache.getInstance().put(msg.getUsername(), ctx.channel());
        ctx.channel().writeAndFlush(RegisterWebSocketRespMessage.builder().build());

        // 将当前用户上线的消息通知到所有客户端
        Map<String, Channel> clients = ChannelCache.getInstance().list();
        Iterator<String> iterator = clients.keySet().iterator();
        while (iterator.hasNext()) {
            String username = iterator.next();
            if (!username.equals(msg.getUsername())) {
                OnlineNotificationWebSocketRespMessage notificationMsg = OnlineNotificationWebSocketRespMessage.builder()
                        .onlineTime(DateUtils.format(now))
                        .onlineUsername(msg.getUsername()).build();
                clients.get(username).writeAndFlush(JSONObject.toJSONString(notificationMsg));
            }
        }

    }
}
