package controller;

import client.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClientFormController {
    public AnchorPane pane;
    public ScrollPane scrollPain;
    public VBox vBox;
    public JFXTextField txtMsg;
    public Text txtLabel;
    public JFXButton emojiButton;

    private Client client;

    public void initialize(){
        try {
            client = new Client(new Socket("localhost",3001));
            System.out.println("Client connected");

            vBox.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                    scrollPain.setVvalue((Double) newValue);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.receivedMessageFromServer(vBox);

    }

    public void txtMsgOnAction(ActionEvent actionEvent) {
        sendButtonOnAction(actionEvent);
    }

    public void sendButtonOnAction(ActionEvent actionEvent) {
        sendMsg(txtMsg.getText());
    }

    private void sendMsg(String msgToSend) {
        if (!msgToSend.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5,5,0,10));

            Text text = new Text(msgToSend);
            text.setStyle("-fx-font-size: 14");
            TextFlow textFlow = new TextFlow(text);

//            #0693e3 #37d67a #40bf75
            textFlow.setStyle("-fx-background-color: #0693e3; -fx-font-weight: bold; -fx-color: white; -fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5,10,5,10));
            text.setFill(Color.color(1,1,1));

            hBox.getChildren().add(textFlow);

            HBox hBoxTime = new HBox();
            hBoxTime.setAlignment(Pos.CENTER_RIGHT);
            hBoxTime.setPadding(new Insets(0,5,5,10));
            String stringTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            Text time = new Text(stringTime);
            time.setStyle("-fx-font-size: 8");

            hBoxTime.getChildren().add(time);


            vBox.getChildren().add(hBox);
            vBox.getChildren().add(hBoxTime);

            client.sendMessageToServer(msgToSend);

            txtMsg.clear();
        }
    }

    public static void receiveMessage(String msgFromServer, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: #abb8c3; -fx-font-weight: bold; -fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5,10,5,10));
        text.setFill(Color.color(0,0,0));

        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }

    public void attachedButtonOnAction(ActionEvent actionEvent) {

    }
}
