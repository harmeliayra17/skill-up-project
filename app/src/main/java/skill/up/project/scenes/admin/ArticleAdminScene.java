package skill.up.project.scenes.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import skill.up.project.controllers.AdminController;
import skill.up.project.controllers.ArticleController;
import skill.up.project.models.Admin;
import skill.up.project.models.Article;
import skill.up.project.utils.UIUtil;

import java.io.File;
import java.util.List;

public class ArticleAdminScene {
    private Stage stage;

    public ArticleAdminScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("root3");

        Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");
        root.getChildren().add(rectangleHome);

        // Top bar
        Label labelTitleHome = UIUtil.createLabel("ARTIKEL", 28, 18);
        labelTitleHome.getStyleClass().add("label-title-admin");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-admin");

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        // Rectangles and other elements

        Button buttonAdd = UIUtil.createButtonWithImage("/images/Add.png", 561, 67, 134, 40);
        buttonAdd.setOnAction(e -> {
            AddArtikel addArtikel = new AddArtikel(stage);
            addArtikel.show(id);
        });

        Label labelAddEvent = UIUtil.createLabel("Artikel yang telah ditambahkan", 131, 77);
        labelAddEvent.getStyleClass().add("label-add");

        Button buttonHomeList = UIUtil.createButtonWithImage("/images/Book_fill.png", 15, 45, 50, 50);
        buttonHomeList.setOnAction(e -> {
            HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
            homeAdminScene.show(id);
        });

        Button buttonArticle = UIUtil.createButtonWithImage("/images/Article2.png", 18, 140, 45, 45);

        Button buttonProfile = UIUtil.createButtonWithImage("/images/User_fill.png", 18, 405, 45, 45);
        buttonProfile.setOnAction(e -> {
            int adminId = id;
            Admin adminProfile = AdminController.getAdminById(adminId);
            if (adminProfile != null) {
                // ProfileAdminScene profileScene = new ProfileAdminScene(stage);
                // profileScene.show(adminId);
            } else {
                // Handle jika tidak dapat menemukan pengguna
            }
        });

        // Articles section
        List<Article> articlesData = ArticleController.getAllArticles();

        GridPane gridPaneArticle = new GridPane();
        gridPaneArticle.setHgap(10);
        gridPaneArticle.setVgap(10);
        gridPaneArticle.setPadding(new Insets(0));

        int column = 0;
        int row = 0;
        int maxColumns = 7; // Maximum number of articles to display horizontally
        for (Article article : articlesData) {
            if (column >= maxColumns) {
                // Start a new row
                column = 0;
                row++;
            }

            ImageView imageViewArticle = new ImageView();
            imageViewArticle.setFitHeight(100);
            imageViewArticle.setFitWidth(140);

            // Mendapatkan path gambar dan mengatur gambarnya jika tersedia
            if (article.getImagePath() != null && !article.getImagePath().isEmpty()) {
                File fileImage = new File(article.getImagePath());
                if (fileImage.exists()) {
                    Image imageArticle = new Image(fileImage.toURI().toString());
                    imageViewArticle.setImage(imageArticle);

                    // Apply clipping with rounded corners
                    Rectangle clip = new Rectangle(140, 100);
                    clip.setArcWidth(38);
                    clip.setArcHeight(38);
                    imageViewArticle.setClip(clip);
                }
            }

            VBox vBoxImageArticle = new VBox(imageViewArticle);
            vBoxImageArticle.setAlignment(Pos.CENTER);

            Label labelTitleArticle = new Label(article.getTitle());
            labelTitleArticle.setFont(Font.font("Poppins", FontWeight.BOLD, 11));
            Label labelLink = new Label(article.getLink());
            VBox vBoxArtikel = new VBox(labelTitleArticle, labelLink);
            vBoxArtikel.setPadding(new Insets(15, 15, 15, 15));
            vBoxArtikel.setSpacing(5);

            VBox vBoxArticle = new VBox(vBoxImageArticle, vBoxArtikel);
            vBoxArticle.setStyle("-fx-border-color: #7D1935; -fx-border-width: 2px; -fx-border-radius: 20px;");
            vBoxArticle.setPrefWidth(180);

            // Menambahkan event handler untuk membuka link saat gambar diklik
            vBoxArticle.setOnMouseClicked(event -> {
                UpdateArtikel detailArtikelScene = new UpdateArtikel(stage, article);
                detailArtikelScene.show(id);
            });
            gridPaneArticle.add(vBoxArticle, column, row);
            column++;
        }

        ScrollPane scrollPane = new ScrollPane(gridPaneArticle);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Mengizinkan pengguliran horizontal
        scrollPane.setStyle("-fx-background-color: #FFFFFF;"); // Menetapkan warna latar belakang scroll pane menjadi putih
        scrollPane.setLayoutX(132);
        scrollPane.setLayoutY(125);
        scrollPane.setPrefHeight(335);
        scrollPane.setPrefWidth(568); // Set the width of the ScrollPane to match the Scene width

        // Add all elements to the root pane
        root.getChildren().addAll(labelTitleHome, labelAddEvent, imageViewSmallLogo, labelTitleName, buttonHomeList, buttonArticle, buttonProfile, buttonAdd, scrollPane);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
