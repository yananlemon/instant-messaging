package site.ilemon.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.ilemon.utils.BeanManager;
import site.ilemon.websocket.constants.WebSocketConstants;
import site.ilemon.websocket.message.req.GroupMsgWebSocketReqMessage;
import site.ilemon.websocket.message.req.RegisterWebSocketReqMessage;
import site.ilemon.websocket.message.resp.HeartbeatRespMessage;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Slf4j
@Component
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("收到浏览器的消息："+ msg.text());
        JSONObject jsonObject = JSONObject.parseObject(msg.text());
        String event = (String) jsonObject.get("event");
        switch (event) {
            case WebSocketConstants.Event.REGISTER_EVENT_REQ:
                BeanManager.getBean(RegisterWebSocketHandler.class).handle(ctx, JSONObject.parseObject(msg.text(), RegisterWebSocketReqMessage.class));
                break;
            case WebSocketConstants.Event.GROUP_MSG_EVENT_REQ:
                BeanManager.getBean(GroupMsgWebSocketHandler.class).handle(ctx, JSONObject.parseObject(msg.text(), GroupMsgWebSocketReqMessage.class));
                break;
            default:
                log.error("不能处理");
                break;
        }
    }

    /**
     * 一段时间未进行读写操作 回调
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // TODO Auto-generated method stub
        super.userEventTriggered(ctx, evt);

        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;
            String msg = JSONObject.toJSONString(HeartbeatRespMessage.builder().currentTime(System.currentTimeMillis()).build());
            if (event.state().equals(IdleState.READER_IDLE)) {
                ctx.channel().writeAndFlush(msg);

            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                ctx.channel().writeAndFlush(msg);
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                ctx.channel().writeAndFlush(msg);

            }

        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage());
    }
}
