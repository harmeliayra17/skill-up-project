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
import skill.up.project.controllers.ArticleController;
import skill.up.project.controllers.WebinarController;
import skill.up.project.models.Article;
import skill.up.project.models.Webinar;
import skill.up.project.utils.UIUtil;

import java.io.File;

public class AddArtikel {
    private Stage stage;
    private Article article;

    public AddArtikel(Stage stage) {
        this.stage = stage;
        this.article = new Article(0, null, null, null);
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

        TextField textFieldArtikelTitle = UIUtil.createTextField("Masukkan judul artikel", 41, 75);
        textFieldArtikelTitle.setPrefWidth(653);
        textFieldArtikelTitle.getStyleClass().add("label-name-desc");

        TextField textFieldLink = UIUtil.createTextField("Masukkan link artikel", 326, 134);
        textFieldLink.setPrefWidth(368);
        textFieldLink.getStyleClass().add("text-field-update");

        Button buttonBack = UIUtil.createButtonWithImage("/images/Singn_out.png", 10, 5, 35, 35);
        buttonBack.setOnAction(e -> {
            HomeAdminScene webinarScene = new HomeAdminScene(stage);
            webinarScene.show(id);
        });

        ImageView addImages = UIUtil.createImageView("/images/AddPhoto.png", 269, 315, 41, 134);
        addImages.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                String imagePath = selectedFile.toURI().toString();
                article.setImagePath(imagePath);

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

        Button buttonUpdate = UIUtil.createButton("Submit", 456, 220);
        buttonUpdate.getStyleClass().add("button-register-desc");

        buttonUpdate.setOnAction(e -> {
            try {
                String updatedName = textFieldArtikelTitle.getText();
                String updatedLink = textFieldLink.getText();

                article.setTitle(updatedName);
                article.setLink(updatedLink);

                if (ArticleController.updateArticle(article.getId(), updatedName, article.getImagePath(), updatedLink)) {
                    HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
                    homeAdminScene.show(id);
                } else {
                    System.out.println("Failed to update artikel in the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, textFieldLink, textFieldArtikelTitle, addImages, buttonUpdate);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
