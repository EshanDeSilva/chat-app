package client;

import controller.ServerFormController;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket){
        try{
            System.out.println(socket.toString());
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,"something went wrong in Client").show();
            e.printStackTrace();
            closeEverything(this.socket,bufferedReader,bufferedWriter);
        }
    }

    public Socket getSocket(){
        return socket;
    }

    public void sendMessageToServer(String msg){
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

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"something went wrong").show();
        }
    }

    public void receivedMessageFromServer(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        String msg = bufferedReader.readLine();
                        ServerFormController.receiveMessage(msg,vBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR,"something went wrong in received msg from server to client").show();
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }
}
