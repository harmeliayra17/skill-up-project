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
            ArticleAdminScene articleAdminScene = new ArticleAdminScene(stage);
            articleAdminScene.show(id);
        });

        ImageView addImages = UIUtil.createImageView("/images/AddPhoto.png", 269, 315, 41, 134);
        addImages.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);
        
            if (selectedFile != null) {
                String imagePath = selectedFile.getAbsolutePath(); // Menggunakan absolute path
                article.setImagePath(imagePath);
        
                Image image = new Image(selectedFile.toURI().toString());
                addImages.setImage(image);
                addImages.setFitWidth(269);
                addImages.setFitHeight(315);
        
                Rectangle clip = new Rectangle(269, 300);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                addImages.setClip(clip);
            }
        });

        Label labelStatus = UIUtil.createLabel("", 350, 260);
        labelStatus.getStyleClass().add("label-status");
        
        Button buttonAdd = UIUtil.createButton("Submit", 456, 220);
        buttonAdd.getStyleClass().add("button-register-desc");

        buttonAdd.setOnAction(e -> {
            try {
                String newArticleName = textFieldArtikelTitle.getText();
                String newArticleLink = textFieldLink.getText();

                if (newArticleName == null || newArticleName.trim().isEmpty()) {
                    labelStatus.setText("Nama tidak boleh kosong!");
                    return;
                }
        
                if (newArticleLink == null || newArticleLink.trim().isEmpty()) {
                    labelStatus.setText("Link tidak boleh kosong!");;
                    return;
                }

                if (ArticleController.addArticle(newArticleName, article.getImagePath(), newArticleLink)) {
                    ArticleAdminScene articleAdminScene = new ArticleAdminScene(stage);
                    articleAdminScene.show(id);
                } else {
                    System.out.println("Failed to add article to the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        root.getChildren().addAll(imageViewSmallLogo, labelTitleName, buttonBack, labelTitle, textFieldLink, textFieldArtikelTitle, addImages, buttonAdd, labelStatus);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
