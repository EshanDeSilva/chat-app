package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtName;

    public void initialize(){

    }

    public void logInButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ClientForm.fxml"))));
        primaryStage.setTitle(txtName.getText());
        primaryStage.centerOnScreen();
        primaryStage.show();

        txtName.clear();
    }
}
