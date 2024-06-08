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
import skill.up.project.controllers.UserController;
// import skill.up.project.controllers.AdminController;
import skill.up.project.controllers.WebinarController;
// import skill.up.project.models.Admin;
import skill.up.project.models.User;
import skill.up.project.models.Webinar;
import skill.up.project.utils.UIUtil;

import java.io.File;
import java.util.List;

public class HomeAdminScene {
    private Stage stage;

    public HomeAdminScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("root3");

        Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");
        root.getChildren().add(rectangleHome);

        Label labelTitleHome = UIUtil.createLabel("WEBINAR DAN PELATIHAN", 28, 18);
        labelTitleHome.getStyleClass().add("label-title-admin");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-admin");

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        Button buttonAdd = UIUtil.createButtonWithImage("/images/Add.png", 561, 67, 134, 40);
        buttonAdd.setOnAction(e -> {
            AddWebinar addWebinar = new AddWebinar(stage);
            addWebinar.show(id);
        });


        Label labelAddEvent = UIUtil.createLabel("Event yang telah ditambahkan", 131, 77);
        labelAddEvent.getStyleClass().add("label-add");

        Button buttonHomeList = UIUtil.createButtonWithImage("/images/Book_Fill3.png", 15, 45, 50, 50);

        Button buttonArticle = UIUtil.createButtonWithImage("/images/Article1.png", 18, 140, 45, 45);
        buttonArticle.setOnAction(e -> {
            ArticleAdminScene articleAdminScene = new ArticleAdminScene(stage);
            articleAdminScene.show(id);
        });

        Button buttonProfile = UIUtil.createButtonWithImage("/images/User_fill.png", 18, 405, 45, 45);
        buttonProfile.setOnAction(e -> {
            User adminProfile = UserController.getUserById(id);
            if (adminProfile != null) {
                ProfileAdminScene profileScene = new ProfileAdminScene(stage);
                profileScene.show(id);
            } else {
                // Handle jika tidak dapat menemukan pengguna
            }
        });

        List<Webinar> webinarsData = WebinarController.getAllWebinar();

        GridPane gridPaneWebinars = new GridPane();
        gridPaneWebinars.setHgap(10);
        gridPaneWebinars.setPadding(new Insets(0));

        int column = 0;
        for (Webinar webinar : webinarsData) {
            ImageView imageViewWebinar = new ImageView();
            imageViewWebinar.setFitHeight(200);
            imageViewWebinar.setFitWidth(166);

            if (webinar.getImagePath() != null && !webinar.getImagePath().isEmpty()) {
                File fileImage = new File(webinar.getImagePath());
                if (fileImage.exists()) {
                    Image imageWebinar = new Image(fileImage.toURI().toString());
                    imageViewWebinar.setImage(imageWebinar);

                    Rectangle clip = new Rectangle(166, 200);
                    clip.setArcWidth(35);
                    clip.setArcHeight(35);
                    imageViewWebinar.setClip(clip);
                }
            }

            VBox vBoxImageWebinar = new VBox(imageViewWebinar);
            vBoxImageWebinar.setAlignment(Pos.CENTER);

            Label labelTitleArticle = new Label(webinar.getName());
            labelTitleArticle.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
            Label labelDesc = new Label(webinar.getDescription());
            VBox vBoxArtikel = new VBox(labelTitleArticle, labelDesc);
            vBoxArtikel.setPadding(new Insets(15, 15, 15, 15));
            vBoxArtikel.setSpacing(5);

            VBox vBoxWebinar = new VBox(vBoxImageWebinar, vBoxArtikel);
            vBoxWebinar.setStyle("-fx-border-color: #7D1935; -fx-border-width: 2px; -fx-border-radius: 20px;");
            vBoxWebinar.setPrefWidth(170);
            vBoxWebinar.setPrefHeight(300);

            vBoxWebinar.setOnMouseClicked(event -> {
                UpdateWebinar detailWebinarScene = new UpdateWebinar(stage, webinar);
                detailWebinarScene.show(id);
            });
            gridPaneWebinars.add(vBoxWebinar, column, 0);
            column++;
        }

        ScrollPane scrollPane = new ScrollPane(gridPaneWebinars);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background-color: #FFFFFF;");
        scrollPane.setLayoutX(132);
        scrollPane.setLayoutY(125);
        scrollPane.setPrefHeight(335);
        scrollPane.setPrefWidth(568);

        root.getChildren().addAll(labelTitleHome, labelAddEvent, imageViewSmallLogo, labelTitleName, buttonHomeList, buttonArticle, buttonProfile, buttonAdd, scrollPane);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
