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
import skill.up.project.models.Webinar;
import skill.up.project.utils.UIUtil;

import java.io.File;
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
        labelTitle.getStyleClass().add("label-title-desc");

        TextField textFieldWebinarName = new TextField(webinar.getName());
        textFieldWebinarName.setLayoutX(51);
        textFieldWebinarName.setLayoutY(75);
        textFieldWebinarName.setStyle("-fx-font-family: 'Poppins'; -fx-text-fill: yellow;");

        Button buttonBack = UIUtil.createButtonWithImage("/images/Singn_out.png", 10, 5, 35, 35);
        buttonBack.setOnAction(e -> {
            HomeAdminScene webinarScene = new HomeAdminScene(stage);
            webinarScene.show(id);
        });

        ImageView imageViewDesc = new ImageView();
        imageViewDesc.setFitWidth(269);
        imageViewDesc.setFitHeight(335);
        imageViewDesc.setLayoutX(41);
        imageViewDesc.setLayoutY(111);
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

        Button buttonChangeImage = new Button("Change Image");
        buttonChangeImage.setLayoutX(41);
        buttonChangeImage.setLayoutY(460);
        buttonChangeImage.getStyleClass().add("button-change-image");

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
        textAreaWebinarDesc.setWrapText(true);
        textAreaWebinarDesc.getStyleClass().add("label-desc");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(textAreaWebinarDesc);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(368, 265);
        scrollPane.setLayoutX(323);
        scrollPane.setLayoutY(113);
        scrollPane.setPadding(new Insets(2));
        scrollPane.getStyleClass().add("scroll-pane-desc");

        Button buttonUpdate = UIUtil.createButton("UPDATE", 455, 410);
        buttonUpdate.getStyleClass().add("button-register-desc");

        buttonUpdate.setOnAction(e -> {
            try {
                String updatedName = textFieldWebinarName.getText();
                String updatedDescription = textAreaWebinarDesc.getText();

                webinar.setName(updatedName);
                webinar.setDescription(updatedDescription);

                updateWebinarInDatabase(webinar);

                HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
                homeAdminScene.show(id);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, textFieldWebinarName, imageViewDesc, scrollPane, buttonUpdate, buttonChangeImage);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }

    private void updateWebinarInDatabase(Webinar webinar) {
        // Implement the logic to update the webinar in your database
        // This might involve calling a method from your database access layer
    }
}
