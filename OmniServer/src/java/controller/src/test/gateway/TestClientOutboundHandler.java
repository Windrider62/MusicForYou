package test.gateway;

import gateway.utility.BufferUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

//TODO incompleet

public class TestClientOutboundHandler extends ChannelOutboundHandlerAdapter{
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        BufferUtil.serialize(msg);


        super.write(ctx, msg, promise);


    }
}
