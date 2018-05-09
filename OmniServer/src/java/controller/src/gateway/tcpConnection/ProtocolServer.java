package gateway.tcpConnection;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetSocketAddress;

public class ProtocolServer{

    private int port;
    private String ip;
    private static ChannelGroup allChannels;

    public ProtocolServer(String address, int port) {
        this.ip = address;
        this.port = port;
        allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            ch.pipeline().addLast(new PSInboundHandler());
                            allChannels.add(ch);
//                            System.out.println(ch.id());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(new InetSocketAddress(ip, port)).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void getAllChannelCtx(){
        for (Channel chn : allChannels) {
            System.out.println(chn.id().toString());
        }
    }
}
