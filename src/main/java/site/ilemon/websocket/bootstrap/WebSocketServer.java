package site.ilemon.websocket.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.ilemon.config.ApplicationConfig;
import site.ilemon.websocket.codec.MessageEncoder;
import site.ilemon.websocket.codec.StringEncoder;
import site.ilemon.websocket.handler.ExceptionCaughtHandler;
import site.ilemon.websocket.handler.TextWebSocketFrameHandler;

import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class WebSocketServer {


    /**
     * 服务实例
     */
    public static final WebSocketServer INSTANCE = new WebSocketServer();

    /**
     * bootstrap
     */
    ServerBootstrap serverBootstrap;

    /**
     * boss
     */
    private EventLoopGroup boss;

    /**
     * work
     */
    private EventLoopGroup work;

    /**
     * 业务层处理线程池
     */
    public static final EventExecutorGroup busiExecutors = new DefaultEventExecutorGroup(5);
    /**
     * 底层处理线程池
     */
    private static final EventExecutorGroup coreExecutors = new DefaultEventExecutorGroup(3);

    public WebSocketServer() {

    }

    /**
     * 启动服务
     */
    public void start() {
        int port = ApplicationConfig.NETTY_WS_SERVER_PORT;
        try {
            serverBootstrap = new ServerBootstrap();
            boss = new NioEventLoopGroup();
            work = new NioEventLoopGroup();

            serverBootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //HTTP消息编解码
                            pipeline.addLast(coreExecutors, new HttpServerCodec());
                            //通讯支持
                            pipeline.addLast(coreExecutors, new ChunkedWriteHandler());
                            //HTTP消息组装
                            pipeline.addLast(coreExecutors, new HttpObjectAggregator(1024 * 1024 * 10));
                            pipeline.addLast(coreExecutors, new WebSocketServerProtocolHandler("/ws"));
                            pipeline.addLast(coreExecutors, new IdleStateHandler(20, 15, 10, TimeUnit.SECONDS));
                            pipeline.addLast(busiExecutors, new StringEncoder());
                            pipeline.addLast(busiExecutors, new MessageEncoder());
                            pipeline.addLast(busiExecutors, new TextWebSocketFrameHandler());
                            pipeline.addLast(busiExecutors, new ExceptionCaughtHandler());
                        }
                    });

            log.info("启动websocket服务端，端口号：" + port);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            log.error("websocket服务端异常", e);
        } finally {
            this.close();
        }

    }

    /**
     * 关闭服务
     */
    public void close() {
        log.info("关闭websocket服务端");
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

}