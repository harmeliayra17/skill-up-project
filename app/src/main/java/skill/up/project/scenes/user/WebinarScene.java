package skill.up.project.scenes.user;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import skill.up.project.controllers.WebinarController;
import skill.up.project.models.Webinar;
import skill.up.project.utils.UIUtil;

public class WebinarScene {
    private Stage stage;

    public WebinarScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        Pane root = new Pane();
        root.getStyleClass().add("root2");

        // Top bar
        Label labelTitleHome = UIUtil.createLabel("WEBINAR DAN PELATIHAN", 28, 18);
        labelTitleHome.getStyleClass().add("label-title-home");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-home");

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");

        Button buttonHome = UIUtil.createButtonWithImage("/images/Home_fill2.png", 15, 45, 50, 50);
        buttonHome.setOnAction(e -> {
            HomeTest homeScene = new HomeTest(stage);
            homeScene.show(id);
        });

        Button buttonEvent = UIUtil.createButtonWithImage("/images/Book_fill2.png", 18, 140, 45, 45);

        Button buttonProfile = UIUtil.createButtonWithImage("/images/User_fill.png", 18, 405, 45, 45);
        buttonProfile.setOnAction(e -> {
            ProfileScene profileScene = new ProfileScene(stage);
            profileScene.show(id);
        });


        // Webinars section
        List<Webinar> webinarsData = WebinarController.getAllWebinar();

        VBox vboxWebinars = new VBox(10);
        vboxWebinars.setPadding(new Insets(0));

        for (Webinar webinar : webinarsData) {
            ImageView imageViewWebinar = new ImageView();
            imageViewWebinar.setFitHeight(75);
            imageViewWebinar.setFitWidth(290);
            imageViewWebinar.setPreserveRatio(false);

            // Set image if available
            if (webinar.getImagePath() != null && !webinar.getImagePath().isEmpty()) {
                File fileImage = new File(webinar.getImagePath());
                if (fileImage.exists()) {
                    Image imageWebinars = new Image(fileImage.toURI().toString());
                    imageViewWebinar.setImage(imageWebinars);

                    // Clip the image to crop it from the top
                    Rectangle clip = new Rectangle(290, 75);
                    clip.setArcWidth(28);
                    clip.setArcHeight(28);
                    imageViewWebinar.setClip(clip);

                    // Use viewport to crop the image to fit the desired dimensions
                    double imageWidth = imageWebinars.getWidth();
                    double imageHeight = imageWebinars.getHeight();
                    if (imageHeight > 75) {
                        double cropHeight = 75 * imageWidth / 290;
                        imageViewWebinar.setViewport(new Rectangle2D(0, 0, imageWidth, cropHeight));
                    }
                }
            }
            
            Label labelTitleWebinar = new Label(webinar.getName());
            labelTitleWebinar.setFont(Font.font("Poppins", FontWeight.BOLD, 16));

            Label labelDesc = new Label(webinar.getDescription());
            labelDesc.setWrapText(true);
            
            VBox vBoxText = new VBox(labelTitleWebinar, labelDesc);
            vBoxText.setPadding(new Insets(0, 15, 0, 15));
            vBoxText.setSpacing(0);
            
            HBox hBoxWebinar = new HBox(imageViewWebinar, vBoxText);
            hBoxWebinar.setStyle("-fx-border-color: #7D1935; -fx-border-width: 2px; -fx-border-radius: 15px;");
            hBoxWebinar.setPrefWidth(578); 
            hBoxWebinar.setPrefHeight(75);
            hBoxWebinar.setAlignment(Pos.CENTER_LEFT);
            
            vboxWebinars.getChildren().add(hBoxWebinar);

            // Menambahkan event handler untuk menampilkan detail webinar saat HBox di klik
            hBoxWebinar.setOnMouseClicked(event -> {
                DetailWebinarScene detailWebinarScene = new DetailWebinarScene(stage, webinar);
                detailWebinarScene.show(id);
            });
        }

        ScrollPane scrollPane = new ScrollPane(vboxWebinars);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #FFFFFF;");
        scrollPane.setLayoutX(105);
        scrollPane.setLayoutY(65);
        scrollPane.setPrefHeight(390);
        scrollPane.setPrefWidth(608);

        // Add all elements to the root pane
        root.getChildren().addAll(labelTitleHome, imageViewSmallLogo, labelTitleName, rectangleHome, buttonHome, buttonEvent, buttonProfile, scrollPane);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
