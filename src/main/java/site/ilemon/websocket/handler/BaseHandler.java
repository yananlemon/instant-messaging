package site.ilemon.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import site.ilemon.websocket.message.AbstractWebSocketMessage;

/**
 *
 */
public abstract class BaseHandler<T extends AbstractWebSocketMessage> {

  /**
   * 消息处理
   *
   * @param ctx
   * @param msg
   */
  public abstract void handle(ChannelHandlerContext ctx, T msg);

}
