package site.ilemon.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * Author: yangwen
 * Date:  2020/10/20 7:32 下午
 */
@Slf4j
public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // TODO
    log.info("ExceptionCaughtHandler exceptionCaught " + cause.getMessage());
  }

}