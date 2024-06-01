package skill.up.project.scenes.user;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import skill.up.project.models.Webinar;
import skill.up.project.utils.UIUtil;
import java.io.File;
import java.awt.Desktop;
import java.net.URI;

public class DetailWebinarScene {
    private Stage stage;
    private Webinar webinar;

    public DetailWebinarScene(Stage stage, Webinar webinar) {
        this.stage = stage;
        this.webinar = webinar;
    }

    public void show(int id) {
        Pane root = new Pane();
        root.getStyleClass().add("root2");

        Region rectangleHome = UIUtil.createRoundedRectangle(698, 412, 21, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");
        root.getChildren().add(rectangleHome);

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-home");
        
        Label labelTitle = UIUtil.createLabel("Detail Webinar", 66, 13);
        labelTitle.getStyleClass().add("label-title-home");
        
        // Region rectangleHome = UIUtil.createRoundedRectangle(698, 412, 21, 55);
        // rectangleHome.getStyleClass().add("rounded-rectangle");
        
        Label labelWebinarName = UIUtil.createLabel(webinar.getName(), 41, 75);
        labelWebinarName.getStyleClass().add("label-title-event");
        
        Button buttonBack = UIUtil.createButtonWithImage("/images/Singn_out.png", 10, 5, 35, 35);
        buttonBack.setOnAction(e -> {
            WebinarScene webinarScene = new WebinarScene(stage);
            webinarScene.show(id);
        });

        //TODO button profile, tambahkan atau tidak?

        ImageView imageViewDesc = new ImageView();
        imageViewDesc.setFitWidth(269);
        imageViewDesc.setFitHeight(335);
        imageViewDesc.setLayoutX(41);
        imageViewDesc.setLayoutY(111);
        imageViewDesc.getStyleClass().add("image-skillup"); // Add this CSS class for border radius

        if (webinar.getImagePath() != null && !webinar.getImagePath().isEmpty()) {
            File fileImage = new File(webinar.getImagePath());
            if (fileImage.exists()) {
                Image imageWebinars = new Image(fileImage.toURI().toString());
                imageViewDesc.setImage(imageWebinars);

                Rectangle clip = new Rectangle(269, 335);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                imageViewDesc.setClip(clip);
            }
        }

        Label labelWebinarDesc = UIUtil.createLabel(webinar.getDescription(), 346, 115);
        labelWebinarDesc.setPrefWidth(368);
        labelWebinarDesc.setWrapText(true);
        labelWebinarDesc.getStyleClass().add("label-desc");

        // Create a ScrollPane for the webinar description
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(labelWebinarDesc);
        scrollPane.setFitToWidth(true); 
        scrollPane.setPrefHeight(265);
        scrollPane.setLayoutX(323);
        scrollPane.setLayoutY(113);
        scrollPane.setPadding(new Insets(2));
        scrollPane.getStyleClass().add("scroll-pane-desc");

        Button buttonJoin = UIUtil.createButton("DAFTAR", 455, 410);
        buttonJoin.getStyleClass().add("button-register-desc");

        // Add button action here
        buttonJoin.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(webinar.getLink()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, labelWebinarName, imageViewDesc, scrollPane, buttonJoin);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
