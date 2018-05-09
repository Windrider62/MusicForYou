package test.gateway;

import gateway.protocol.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

public class ClientTestSocket {
    private ObjectOutputStream out;
    private SocketChannel clientSocket;

    public void startConnection(String ip, int port) throws IOException {
        try
        {
            clientSocket = SocketChannel.open();
            clientSocket.connect(new InetSocketAddress(ip, port));
            out = new ObjectOutputStream(new BufferedOutputStream(Channels.newOutputStream(clientSocket)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message cmd) throws IOException {
        out.writeObject(cmd);
    }

    public void stopConnection() {
        try {
            out.close();
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
