package skill.up.project.scenes.popUp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skill.up.project.scenes.LandingScene;
import skill.up.project.scenes.admin.ProfileAdminScene;
import skill.up.project.scenes.user.ProfileScene;
import skill.up.project.utils.UIUtil;

public class PopUpOutAdmin {
    private Stage stage;

    public PopUpOutAdmin(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        Pane root = new Pane();

        // Set background image
        BackgroundImage backgroundImage = UIUtil.createBackgroundImage("/images/home3.png", 740, 480);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

        // Labels
        Label labelHalo = UIUtil.createLabel("Apakah anda yakin untuk keluar?", 220, 190);
        labelHalo.getStyleClass().add("title-text-delete");

        // Buttons
        Button buttonYa = UIUtil.createButton("Ya", 283, 254);
        buttonYa.getStyleClass().add("button-ya");
        buttonYa.setOnAction(e -> {
            LandingScene landingScene = new LandingScene(stage);
            landingScene.show();
        });

        Button buttonBatal = UIUtil.createButton("Batal", 396, 254);
        buttonBatal.getStyleClass().add("button-batal");
        buttonBatal.setOnAction(e -> {
            ProfileAdminScene profileAdminScene = new ProfileAdminScene(stage);
            profileAdminScene.show(id);
        });

        root.getChildren().addAll(labelHalo, buttonYa, buttonBatal);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
