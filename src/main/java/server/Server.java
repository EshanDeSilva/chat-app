package server;

import controller.ServerFormController;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server() throws IOException {
        serverSocket = new ServerSocket(3001);
        try{
            socket=serverSocket.accept();
            System.out.println("client socket added"+socket.toString());
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"something went wrong").show();
        }
    }

    public void sendMessageToClient(String msg){
        try{
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"msg not send").show();
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void receiveMessageFromClient(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        String msg = bufferedReader.readLine();
                        ServerFormController.receiveMessage(msg,vBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR,"something went wrong in received msg from client to server").show();
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }
}
