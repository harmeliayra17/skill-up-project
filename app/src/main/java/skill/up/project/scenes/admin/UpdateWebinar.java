package skill.up.project.scenes.admin;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import skill.up.project.controllers.WebinarController;
import skill.up.project.models.Webinar;
import skill.up.project.utils.UIUtil;

import java.io.File;

public class UpdateWebinar {
    private Stage stage;
    private Webinar webinar;

    public UpdateWebinar(Stage stage, Webinar webinar) {
        this.stage = stage;
        this.webinar = webinar;
    }
    public void show(int id) {
        Pane root = new Pane();
        root.getStyleClass().add("root3");

        Region rectangleHome = UIUtil.createRoundedRectangle(698, 412, 21, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");
        root.getChildren().add(rectangleHome);

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-admin");

        Label labelTitle = UIUtil.createLabel("Edit Event", 66, 18);
        labelTitle.getStyleClass().add("label-title-admin");

        TextField textFieldWebinarName = UIUtil.createTextField(webinar.getName(), 41, 65);
        textFieldWebinarName.setPrefWidth(471);
        textFieldWebinarName.getStyleClass().add("label-name-desc");

        TextField textFieldLink = UIUtil.createTextField(webinar.getLink(), 326, 353);
        textFieldLink.setPrefWidth(368);
        textFieldLink.getStyleClass().add("text-field-update");

        Button buttonDelete = UIUtil.createButtonWithImage("/images/Delete.png", 573, 58, 120, 40);

        Button buttonBack = UIUtil.createButtonWithImage("/images/Singn_out.png", 10, 5, 35, 35);
        buttonBack.setOnAction(e -> {
            HomeAdminScene webinarScene = new HomeAdminScene(stage);
            webinarScene.show(id);
        });

        ImageView imageViewDesc = new ImageView();
        imageViewDesc.setFitWidth(269);
        imageViewDesc.setFitHeight(335);
        imageViewDesc.setLayoutX(41);
        imageViewDesc.setLayoutY(113);
        imageViewDesc.getStyleClass().add("image-skillup");

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

        Button buttonChangeImage = UIUtil.createButton("Ubah Gambar", 385, 404);
        buttonChangeImage.getStyleClass().add("button-register-desc");

        buttonChangeImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                imageViewDesc.setImage(newImage);
                webinar.setImagePath(selectedFile.getAbsolutePath());

                Rectangle clip = new Rectangle(269, 335);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                imageViewDesc.setClip(clip);
            }
        });

        TextArea textAreaWebinarDesc = new TextArea(webinar.getDescription());
        textAreaWebinarDesc.setPrefWidth(368);
        textAreaWebinarDesc.setPrefHeight(215);
        textAreaWebinarDesc.setWrapText(true);
        textAreaWebinarDesc.getStyleClass().add("label-desc");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(textAreaWebinarDesc);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(368, 215);
        scrollPane.setLayoutX(326);
        scrollPane.setLayoutY(113);
        scrollPane.setPadding(new Insets(2));
        scrollPane.getStyleClass().add("scroll-pane-desc");

        Button buttonUpdate = UIUtil.createButton("Perbarui", 532, 404);
        buttonUpdate.getStyleClass().add("button-register-desc");

        buttonUpdate.setOnAction(e -> {
            try {
                String updatedName = textFieldWebinarName.getText();
                String updatedDescription = textAreaWebinarDesc.getText();

                webinar.setName(updatedName);
                webinar.setDescription(updatedDescription);

                if (WebinarController.updateWebinar(webinar.getId(), updatedName, webinar.getImagePath(), webinar.getLink(), updatedDescription)) {
                    HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
                    homeAdminScene.show(id);
                } else {
                    System.out.println("Failed to update webinar in the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, textFieldLink, textFieldWebinarName, imageViewDesc, scrollPane, buttonUpdate, buttonDelete, buttonChangeImage);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
