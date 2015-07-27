package server;

import org.eclipse.jetty.server.Server;

public class SimpleServer {

    public static void main(String[] args) {
        Server server = new Server(8888);
        server.setHandler(new Handler() {
        });
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
