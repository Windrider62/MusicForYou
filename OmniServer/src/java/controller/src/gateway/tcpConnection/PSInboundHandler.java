package gateway.tcpConnection;

import static gateway.utility.BufferUtil.deserialize;

import gateway.protocol.ICommand;
import gateway.protocol.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import persistence.UserInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PSInboundHandler extends ChannelInboundHandlerAdapter {

    Map<ChannelId, UserInfo> channels = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try{
            handle(msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private void handle(Object msg) {
        try {
            Object message = deserialize(msg);
            ICommand cmd = ((Message)message).getCommand();
            cmd.processMessage();

            System.out.println(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
