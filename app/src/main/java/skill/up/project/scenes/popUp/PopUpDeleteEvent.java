package skill.up.project.scenes.popUp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skill.up.project.controllers.WebinarController;
import skill.up.project.models.Webinar;
import skill.up.project.scenes.admin.HomeAdminScene;
import skill.up.project.scenes.admin.UpdateWebinar;
import skill.up.project.utils.UIUtil;

public class PopUpDeleteEvent {
    private Stage stage;
    private Webinar webinar;

    public PopUpDeleteEvent(Stage stage, Webinar webinar) {
        this.stage = stage;
        this.webinar = webinar;
    }

    public void show(int id) {
        Pane root = new Pane();

        // Set background image
        BackgroundImage backgroundImage = UIUtil.createBackgroundImage("/images/home2.jpg", 740, 480);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

        // Labels
        Label labelHalo = UIUtil.createLabel("Apakah anda yakin untuk menghapus?", 192, 190);
        labelHalo.getStyleClass().add("title-text-delete");

        // Buttons
        Button buttonYa = UIUtil.createButton("Ya", 283, 254);
        buttonYa.getStyleClass().add("button-ya");
        buttonYa.setOnAction(e -> {
            try {
                if (WebinarController.deleteWebinar(webinar.getId())) {
                    HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
                    homeAdminScene.show(id);
                } else {
                    System.out.println("Failed to delete webinar from the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button buttonBatal = UIUtil.createButton("Batal", 396, 254);
        buttonBatal.getStyleClass().add("button-batal");
        buttonBatal.setOnAction(e -> {
            UpdateWebinar updateWebinarScene = new UpdateWebinar(stage, webinar);
            updateWebinarScene.show(id);
        });

        root.getChildren().addAll(labelHalo, buttonYa, buttonBatal);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
