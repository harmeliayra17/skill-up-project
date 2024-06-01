package skill.up.project.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skill.up.project.scenes.admin.LoginSceneAdmin;
import skill.up.project.scenes.user.LoginScene;
import skill.up.project.utils.UIUtil;

public class LandingScene {
    private Stage stage;

    public LandingScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Pane root = new Pane();

        // Set background image
        BackgroundImage backgroundImage = UIUtil.createBackgroundImage("/images/Splashbg.png", 740, 480);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

        // Labels
        Label labelHalo = UIUtil.createLabel("Halo!", 120, 90);
        labelHalo.getStyleClass().add("title-text-splash");

        Label labelHi = UIUtil.createLabel("Temukan karir sesuai \n" +
                                           "kepribadianmu! Skill Up hadir \n" +
                                           "dengan tes MBTI, webinar, dan\n" +
                                           "pelatihan untuk \n" +
                                           "membantumu mencapai \n" +
                                           "potensimu penuh. Mulai \n" +
                                           "petualangan karirmu \n" +
                                           "sekarang!\"", 120, 125);
        labelHi.getStyleClass().add("label-hi");

        // Buttons
        Button buttonUser = UIUtil.createButton("Login as User", 437, 184);
        buttonUser.getStyleClass().add("button-user");
        buttonUser.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        Button buttonAdmin = UIUtil.createButton("Login as Admin", 437, 238);
        buttonAdmin.getStyleClass().add("button-admin");
        buttonAdmin.setOnAction(e -> {
            LoginSceneAdmin loginSceneAdmin = new LoginSceneAdmin(stage);
            loginSceneAdmin.show();
        });

        root.getChildren().addAll(labelHalo, labelHi, buttonUser, buttonAdmin);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
