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

public class AddWebinar {
    private Stage stage;
    private Webinar webinar;

    public AddWebinar(Stage stage) {
        this.stage = stage;
        this.webinar = new Webinar(0, null, null, null, null);
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

        Label labelTitle = UIUtil.createLabel("Tambah Event", 66, 18);
        labelTitle.getStyleClass().add("label-title-admin");

        TextField textFieldWebinarName = UIUtil.createTextField("Masukkan nama webinar", 41, 65);
        textFieldWebinarName.setPrefWidth(650);
        textFieldWebinarName.getStyleClass().add("label-name-desc");

        TextField textFieldLink = UIUtil.createTextField("Masukkan link webinar", 326, 353);
        textFieldLink.setPrefWidth(368);
        textFieldLink.getStyleClass().add("text-field-update");

        Button buttonBack = UIUtil.createButtonWithImage("/images/Singn_out.png", 10, 5, 35, 35);
        buttonBack.setOnAction(e -> {
            ArticleAdminScene articleScene = new ArticleAdminScene(stage);
            articleScene.show(id);
        });

        ImageView addImages = UIUtil.createImageView("/images/AddPhoto.png", 269, 335, 41, 116);
        addImages.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                String imagePath = selectedFile.toURI().toString();
                webinar.setImagePath(imagePath);

                Image image = new Image(imagePath);
                addImages.setImage(image);
                addImages.setFitWidth(269);
                addImages.setFitHeight(315);

                Rectangle clip = new Rectangle(269, 300);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                addImages.setClip(clip);
            }
        });

        TextArea textAreaWebinarDesc = new TextArea("Masukkan deskripsi webinar");
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

        Button buttonUpdate = UIUtil.createButton("Submit", 464, 404);
        buttonUpdate.getStyleClass().add("button-register-desc");

        buttonUpdate.setOnAction(e -> {
            try {
                String updatedName = textFieldWebinarName.getText();
                String updatedDescription = textAreaWebinarDesc.getText();
                String updatedLink = textFieldLink.getText();

                webinar.setName(updatedName);
                webinar.setDescription(updatedDescription);
                webinar.setLink(updatedLink);

                if (WebinarController.updateWebinar(webinar.getId(), updatedName, webinar.getImagePath(), updatedLink, updatedDescription)) {
                    HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
                    homeAdminScene.show(id);
                } else {
                    System.out.println("Failed to update webinar in the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, textFieldLink, textFieldWebinarName, addImages, scrollPane, buttonUpdate);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
