package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import server.Server;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerFormController {
    public VBox vBox;
    public JFXTextField txtMsg;
    public ScrollPane scrollPain;

    private Server server;

    public void initialize(){
//        new Thread(() -> {
//            try {
//                server = Server.getInstance();
//                server.getServerSocket().accept();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        vBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPain.setVvalue((Double) newValue);
            }
        });

        server.receiveMessageFromClient(vBox);
    }

    public void sendButtonOnAction(ActionEvent actionEvent) {
        String msgToSend = txtMsg.getText();
        if (!msgToSend.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5,5,0,10));

            Text text = new Text(msgToSend);
            text.setStyle("-fx-font-size: 13");
            TextFlow textFlow = new TextFlow(text);

//            #0693e3 #37d67a #40bf75
            textFlow.setStyle("-fx-background-color: #0693e3; -fx-color: white; -fx-background-radius: 20px");
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

//            server.sendMessageToClient(msgToSend);

            txtMsg.clear();
        }
    }

    public void txtMsgOnAction(ActionEvent actionEvent) {
        sendButtonOnAction(actionEvent);
    }

    public void emojiButtonOnAction(ActionEvent actionEvent) {

    }

    public void attachedButtonOnAction(ActionEvent actionEvent) {

    }
}
