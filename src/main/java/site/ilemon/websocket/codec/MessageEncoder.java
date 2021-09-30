package site.ilemon.websocket.codec;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import site.ilemon.websocket.message.AbstractWebSocketMessage;

import java.util.List;

/**
 * 消息编码器
 * Author: yangwen
 * Date:  2019/4/17
 */
public class MessageEncoder extends MessageToMessageEncoder<AbstractWebSocketMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractWebSocketMessage msg, List<Object> list) throws Exception {
        list.add(new TextWebSocketFrame(JSONObject.toJSONString(msg)));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO
    }

}
