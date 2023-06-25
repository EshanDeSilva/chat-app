import server.ServerLauncher;

public class Launcher {
    public static void main(String[] args) {
        ServerLauncher.main(new String[]{});
//
//        new Thread(() -> {
//            server.ServerLauncher.main(new String[]{});
//        }).start();

//        new Thread(() -> {
//            try {
//                Thread.sleep(2000);
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Your class that extends Application
//                        try {
//                            new client.ClientLauncher().start(new Stage());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
