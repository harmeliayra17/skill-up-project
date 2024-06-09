package skill.up.project.scenes.popUp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skill.up.project.controllers.ArticleController;
import skill.up.project.controllers.WebinarController;
import skill.up.project.models.Article;
import skill.up.project.scenes.admin.ArticleAdminScene;
import skill.up.project.scenes.admin.HomeAdminScene;
import skill.up.project.scenes.admin.UpdateArtikel;
import skill.up.project.utils.UIUtil;

public class PopUpDeleteArticle {
    private Stage stage;
    private Article article;

    public PopUpDeleteArticle(Stage stage, Article article) {
        this.stage = stage;
        this.article = article;
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
                if (ArticleController.deleteArticle(article.getId())) {
                    ArticleAdminScene articleAdminScene = new ArticleAdminScene(stage);
                    articleAdminScene.show(id);
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
            UpdateArtikel updateArtikelScene = new UpdateArtikel(stage, article);
            updateArtikelScene.show(id);
        });

        root.getChildren().addAll(labelHalo, buttonYa, buttonBatal);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
