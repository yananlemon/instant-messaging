package site.ilemon.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;
import site.ilemon.utils.DateUtils;
import site.ilemon.websocket.cache.ChannelCache;
import site.ilemon.websocket.message.req.GroupMsgWebSocketReqMessage;
import site.ilemon.websocket.message.req.RegisterWebSocketReqMessage;
import site.ilemon.websocket.message.resp.GroupMessageWebSocketRespMessage;
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
public class GroupMsgWebSocketHandler extends BaseHandler<GroupMsgWebSocketReqMessage> {
    @Override
    public void handle(ChannelHandlerContext ctx, GroupMsgWebSocketReqMessage msg) {
        Date now = new Date();
        // 将当前用户上线的消息通知到所有客户端
        Map<String, Channel> clients = ChannelCache.getInstance().list();
        Iterator<String> iterator = clients.keySet().iterator();
        while (iterator.hasNext()) {
            String username = iterator.next();
            //if (!username.equals(msg.getFrom())) {
                GroupMessageWebSocketRespMessage groupMsg = GroupMessageWebSocketRespMessage.builder()
                        .fromUser(msg.getFrom())
                        .message(msg.getMsg())
                        .sendTime(DateUtils.format(now))
                        .build();

                clients.get(username).writeAndFlush(JSONObject.toJSONString(groupMsg));
            //}
        }

    }
}
