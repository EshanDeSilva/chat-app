package server;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket serverSocket;
    private static Server server;

    private Server() throws IOException {
        setServerSocket(new ServerSocket(3001));
    }

    public static Server getInstance() throws IOException {
        return server!=null ? server:(server=new Server());
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void receiveMessageFromClient(VBox vBox) {

    }
}
