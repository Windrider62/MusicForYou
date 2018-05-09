package test.main;

import gateway.message.Nack;
import gateway.protocol.Command;
import gateway.tcpConnection.ProtocolServer;
import org.testng.annotations.Test;
import test.gateway.ClientTestSocket;
import gateway.message.CurrentStatusReq;
import gateway.protocol.Message;
import gateway.protocol.ProtocolCommand;

import java.io.IOException;


public class AppTest{

    @Test
    public void testConnection_OpenSocket1(){
        ClientTestSocket client = new ClientTestSocket();
        CurrentStatusReq msg = new CurrentStatusReq(4, "test");
        byte b = 0;
        String s = "";
        ProtocolCommand pc = ProtocolCommand.Nack;
        Command cmd = new Command() {
            @Override
            public void processMessage() {
                String strasd = "Hello";
            }
        };

        Message m = new Message(s, s, b, s, pc, cmd);

        try {
            client.startConnection("localhost", 20002);
            client.sendMessage(m);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            client.stopConnection();
        }
    }
}
