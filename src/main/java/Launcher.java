import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        new Thread(() -> {
            ServerLauncher.main(new String[]{});
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // Your class that extends Application
                        try {
                            new ClientLauncher().start(new Stage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
