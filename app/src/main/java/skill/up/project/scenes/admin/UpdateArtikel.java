package skill.up.project.scenes.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import skill.up.project.controllers.ArticleController;
import skill.up.project.models.Article;
import skill.up.project.scenes.popUp.PopUpDeleteArticle;
import skill.up.project.scenes.popUp.PopUpDeleteEvent;
import skill.up.project.utils.UIUtil;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UpdateArtikel {
    private Stage stage;
    private Article article;

    public UpdateArtikel(Stage stage, Article article) {
        this.stage = stage;
        this.article = article;
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

        Label labelTitle = UIUtil.createLabel("Edit Artikel", 66, 18);
        labelTitle.getStyleClass().add("label-title-admin");

        TextField textFieldArticleName = UIUtil.createTextField(article.getTitle(), 41, 75);
        textFieldArticleName.setPrefWidth(471);
        textFieldArticleName.getStyleClass().add("label-name-desc");

        TextField textFieldLink = UIUtil.createTextField(article.getLink(), 326, 134);
        textFieldLink.setPrefWidth(368);
        textFieldLink.getStyleClass().add("text-field-update");

        Button buttonDelete = UIUtil.createButtonWithImage("/images/Delete.png", 573, 58, 120, 40);
        buttonDelete.setOnAction(e -> {
            PopUpDeleteArticle popUpDelete = new PopUpDeleteArticle(stage, article);
            popUpDelete.show(id);
        });

        Button buttonBack = UIUtil.createButtonWithImage("/images/Singn_out.png", 10, 5, 35, 35);
        buttonBack.setOnAction(e -> {
            ArticleAdminScene articleAdminScene = new ArticleAdminScene(stage);
            articleAdminScene.show(id);
        });

        ImageView imageViewDesc = new ImageView();
        imageViewDesc.setFitWidth(269);
        imageViewDesc.setFitHeight(300);
        imageViewDesc.setLayoutX(41);
        imageViewDesc.setLayoutY(134);
        imageViewDesc.getStyleClass().add("image-skillup");

        if (article.getImagePath() != null && !article.getImagePath().isEmpty()) {
            File fileImage = new File(article.getImagePath());
            if (fileImage.exists()) {
                Image imageArticle = new Image(fileImage.toURI().toString());
                imageViewDesc.setImage(imageArticle);

                Rectangle clip = new Rectangle(269, 300);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                imageViewDesc.setClip(clip);
            }
        }

        Button buttonChangeImage = UIUtil.createButton("Ubah Gambar", 385, 220);
        buttonChangeImage.getStyleClass().add("button-register-desc");

        buttonChangeImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                imageViewDesc.setImage(newImage);
                article.setImagePath(selectedFile.getAbsolutePath());

                Rectangle clip = new Rectangle(269, 300);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                imageViewDesc.setClip(clip);
            }
        });

        Button buttonUpdate = UIUtil.createButton("Perbarui", 532, 220);
        buttonUpdate.getStyleClass().add("button-register-desc");

        buttonUpdate.setOnAction(e -> {
            try {
                String updatedName = textFieldArticleName.getText();
                String updatedLink = textFieldLink.getText();

                if (updatedName != null && !updatedName.trim().isEmpty()) {
                    article.setTitle(updatedName);
                } else {
                    updatedName = article.getTitle(); 
                }
        
                if (updatedLink != null && !updatedLink.trim().isEmpty()) {
                    article.setLink(updatedLink);;
                } else {
                    updatedLink = article.getLink();; 
                }

                if (ArticleController.updateArticle(article.getId(), updatedName, article.getImagePath(), updatedLink)) {
                    ArticleAdminScene articleAdminScene = new ArticleAdminScene(stage);
                    articleAdminScene.show(id);
                } else {
                    System.out.println("Failed to update article in the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Menambahkan event handler untuk membuka link saat gambar diklik
        imageViewDesc.setOnMouseClicked(event -> {
            try {
                // Membuka browser default pengguna dengan link artikel
                Desktop.getDesktop().browse(new URI(article.getLink()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, textFieldLink, textFieldArticleName, imageViewDesc, buttonUpdate, buttonDelete, buttonChangeImage);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
