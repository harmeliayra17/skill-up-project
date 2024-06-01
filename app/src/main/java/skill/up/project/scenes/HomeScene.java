package skill.up.project.scenes;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import skill.up.project.controllers.ArticleController;
import skill.up.project.models.Article;
import skill.up.project.utils.UIUtil;

public class HomeScene {
    private Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Pane root = new Pane();
        root.getStyleClass().add("root2");

        Label labelTitleHome = UIUtil.createLabel("HOME", 28, 18);
        labelTitleHome.getStyleClass().add("label-title-home");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-home");

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 18, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");

        Region rectangleAnimated = UIUtil.createRoundedRectangle(587, 148, 115, 70);
        rectangleAnimated.getStyleClass().add("rounded-rectangle-animated");

        Label labelInvitation = UIUtil.createLabel("Temukan kepribadianmu melalui tes MBTI dan dapatkan \nrekomendasi pekerjaan yang sesuai dengan dirimu!", 148, 162);
        labelInvitation.getStyleClass().add("label-invitation");

        Button buttonTest = UIUtil.createButton("Mulai Tes >", 522, 162);
        buttonTest.getStyleClass().add("button-test");

        root.getChildren().addAll(
            labelTitleHome,
            labelTitleName,
            imageViewSmallLogo,
            rectangleHome,
            rectangleAnimated,
            labelInvitation,
            buttonTest
        );

        List<Article> articles = ArticleController.getAllArticles();

        // Create an HBox to hold article components
        HBox articlesBox = new HBox(10);
        articlesBox.setPadding(new Insets(10)); // Padding for spacing
        articlesBox.setLayoutX(99);
        articlesBox.setLayoutY(240);

        // Populate articles
        for (Article article : articles) {
            // Create a Pane for each article to hold all components
            Pane articlePane = new Pane();
            articlePane.setPrefSize(176, 240);
            articlePane.getStyleClass().add("rounded-rectangle-article");

            // Create an ImageView for the article image
            ImageView imageViewArticle = UIUtil.createImageView(article.getImagePath(), 142, 93, 132, 252);
            imageViewArticle.getStyleClass().add("image-article");

            // Create a label for the article title
            Label labelTitleArticle = UIUtil.createLabel(article.getTitle(), 10, 150);
            labelTitleArticle.getStyleClass().add("label-article");

            // Add components to the article pane
            articlePane.getChildren().addAll(imageViewArticle, labelTitleArticle);

            // Add the article pane to the HBox
            articlesBox.getChildren().add(articlePane);
        }

        // Wrap the HBox in a ScrollPane for horizontal scrolling
        ScrollPane scrollPane = new ScrollPane(articlesBox);
        scrollPane.setPrefSize(621, 229);
        scrollPane.setLayoutX(99);
        scrollPane.setLayoutY(240);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStyleClass().add("scroll-pane");

        root.getChildren().add(scrollPane);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
