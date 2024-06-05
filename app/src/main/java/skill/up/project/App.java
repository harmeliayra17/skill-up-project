package skill.up.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import skill.up.project.scenes.LandingScene;
import skill.up.project.utils.SplashScreen;


public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Skill Up");

        // icon aplikasi
        Image imageIcon = new Image(getClass().getClassLoader().getResourceAsStream("images/logoRadius.png"));
        if (imageIcon.isError()) {
            System.out.println("Failed to load application icon.");
        } else {
            stage.getIcons().add(imageIcon);
        }

        // splash screen 3 detik
        SplashScreen splashScreen = new SplashScreen(stage);
        splashScreen.show();

        // buat thread dan di show di scene login 
        new Thread(() -> {
            try {
                Thread.sleep(3000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update UI on JavaFX Application Thread
            Platform.runLater(() -> {
                LandingScene mainScene = new LandingScene(stage);
                mainScene.show();
            });
        }).start();
    }

    public static void main(String[] args) {
        launch();
    }
}
