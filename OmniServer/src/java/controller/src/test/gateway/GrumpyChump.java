package test.gateway;

import java.io.IOException;
import java.net.Socket;

public class GrumpyChump {

    public static void main(String[] args) throws InterruptedException {
        Socket[] sockets = new Socket[50];
        for(int i = 0; i < sockets.length; i++) {
            try {
                sockets[i] = new Socket("localhost", 20002);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100000);
    }
}
