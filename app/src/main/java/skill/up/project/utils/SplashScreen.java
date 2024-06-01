package skill.up.project.utils;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane; // Using AnchorPane for positioning
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class SplashScreen {

    private final Stage stage;

    public SplashScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Create ImageView and Text using UIUtil
        ImageView splashImageView = UIUtil.createImageView("/images/logoRadius.png", 100, 100, 316, 152);
        splashImageView.getStyleClass().add("image-skillup");

        Label titleText = UIUtil.createLabel("Skill Up", 316, 262); // Initial X,Y will be adjusted later
        titleText.getStyleClass().add("title-text-splash");

        // Set background image
        BackgroundImage backgroundImage = UIUtil.createBackgroundImage("/images/Splashbg.png", 740, 480);
        Background background = new Background(backgroundImage);

        // Initialize scene
        Scene splashScene = new Scene(new AnchorPane(), 740, 480);

        // Add ImageView and Text to scene
        AnchorPane root = (AnchorPane) splashScene.getRoot();
        root.setBackground(background);
        root.getChildren().addAll(splashImageView, titleText);

        // Set CSS
        String css = getClass().getResource("/styles/styles.css").toExternalForm();
        splashScene.getStylesheets().add(css);

        // Set scene and show stage
        stage.setScene(splashScene);
        stage.show();
    }
}
