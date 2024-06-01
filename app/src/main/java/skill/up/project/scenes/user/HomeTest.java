package skill.up.project.scenes.user;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import skill.up.project.controllers.ArticleController;
import skill.up.project.controllers.UserController;
import skill.up.project.models.Article;
import skill.up.project.models.User;
import skill.up.project.scenes.mbti.MbtiTest1Scene;
import skill.up.project.utils.UIUtil;
import java.io.File;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HomeTest {
    private Stage stage;

    public HomeTest(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("root2");

        // Top bar
        Label labelTitleHome = UIUtil.createLabel("HOME", 28, 18);
        labelTitleHome.getStyleClass().add("label-title-home");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-home");

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        // Rectangles and other elements
        Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");

        Region rectangleAnimated = UIUtil.createRoundedRectangle(587, 148, 115, 70);
        rectangleAnimated.getStyleClass().add("rounded-rectangle-animated");

        Label labelInvitation = UIUtil.createLabel("Temukan kepribadianmu melalui tes MBTI dan dapatkan \nrekomendasi pekerjaan yang sesuai dengan dirimu!", 143, 162);
        labelInvitation.getStyleClass().add("label-invitation");

        Button buttonTest = UIUtil.createButton("Mulai Tes >", 522, 162);
        buttonTest.getStyleClass().add("button-test");

        buttonTest.setOnAction( e-> {
            MbtiTest1Scene mbtiTest1Scene = new MbtiTest1Scene(stage);
            mbtiTest1Scene.show(id);
        });

        Label labelCareer = UIUtil.createLabel("Artikel Seputar Karir", 123, 228);
        labelCareer.getStyleClass().add("label-career");

        Button buttonHome = UIUtil.createButtonWithImage("/images/Home_fill.png", 15, 45, 50, 50);

        Button buttonEvent = UIUtil.createButtonWithImage("/images/Book_fill.png", 18, 140, 45, 45);
        buttonEvent.setOnAction(e -> {
            WebinarScene webinarScene = new WebinarScene(stage);
            webinarScene.show(id);
        });

        Button buttonProfile = UIUtil.createButtonWithImage("/images/User_fill.png", 18, 405, 45, 45);
        buttonProfile.setOnAction(e -> {
            int userId = id;
            User userProfile = UserController.getUserById(userId);
            if (userProfile!= null) {
                ProfileScene profileScene = new ProfileScene(stage);
                profileScene.show(userId);
            } else {
                // Handle jika tidak dapat menemukan pengguna
            }
        });

        // Articles section
        List<Article> articlesData = ArticleController.getAllArticles();

        GridPane gridPaneArticle = new GridPane();
        gridPaneArticle.setHgap(10);
        gridPaneArticle.setPadding(new Insets(0));

        int column = 0;
        for (Article article : articlesData) {
            ImageView imageViewArticle = new ImageView();
            imageViewArticle.setFitHeight(100);
            imageViewArticle.setFitWidth(140);

            // Mendapatkan path gambar dan mengatur gambarnya jika tersedia
            if (article.getImagePath()!= null &&!article.getImagePath().isEmpty()) {
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
            vBoxArticle.setPrefHeight(100);

            // Menambahkan event handler untuk membuka link saat gambar diklik
            vBoxArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        // Membuka browser default pengguna dengan link artikel
                        Desktop.getDesktop().browse(new URI(article.getLink()));
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });

            gridPaneArticle.add(vBoxArticle, column, 0); // Semua artikel berada di baris 0
            column++;
        }

        ScrollPane scrollPane = new ScrollPane(gridPaneArticle);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Mengizinkan pengguliran horizontal
        scrollPane.setStyle("-fx-background-color: #FFFFFF;"); // Menetapkan warna latar belakang scroll pane menjadi putih
        scrollPane.setLayoutX(115);
        scrollPane.setLayoutY(260);
        scrollPane.setPrefHeight(195);
        scrollPane.setPrefWidth(590); // Set the width of the ScrollPane to match the Scene width

        // Add all elements to the root pane
        root.getChildren().addAll(labelTitleHome, imageViewSmallLogo, labelTitleName, rectangleHome, rectangleAnimated, labelInvitation, labelCareer, buttonTest, buttonHome, buttonEvent, buttonProfile, scrollPane);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}