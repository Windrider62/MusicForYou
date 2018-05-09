package main;

import gateway.tcpConnection.ProtocolServer;

public class App {

    public static void main(String[] args) {
        // TODO: load config file, new Properties();
        // also see security manager


        int port;
        String ip;
        if (args.length > 1) {
            ip = args[0]; // not used atm
            port = Integer.parseInt(args[1]);
        } else {
            ip = "localhost";
            port = 20002;
        }
        App.InitProtocolServer(ip, port);
    }

    private static void InitProtocolServer(String address, int port) {

        try {
            ProtocolServer server = new ProtocolServer(address, port);
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
