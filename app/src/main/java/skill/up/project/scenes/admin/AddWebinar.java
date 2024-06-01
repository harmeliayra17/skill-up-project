package skill.up.project.scenes.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddWebinar {
    private Stage stage;

    public AddWebinar(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Logo Skill Up kanan 
        Image imageSkill = new Image(getClass().getResourceAsStream("/image/logoskill.jpeg"));
        ImageView skillView = new ImageView(imageSkill);
        skillView.setFitHeight(30);
        skillView.setFitWidth(30);

        Label labelSkill = new Label("Skill Up");
        labelSkill.getStyleClass().add("logoColor");

        HBox skillUpBox = new HBox(10, skillView, labelSkill);
        skillUpBox.setAlignment(Pos.CENTER);

        // tombol kembali cicrle jadi kotak kiri atas
        Image circleImage = new Image(getClass().getResourceAsStream("/image/bulatpanah.jpeg"));
        ImageView bulatView = new ImageView(circleImage);
        bulatView.setFitHeight(25);
        bulatView.setFitWidth(25);

        Button btnCircle = new Button();
        btnCircle.setGraphic(bulatView);
        btnCircle.getStyleClass().add("latar2");
        btnCircle.setOnAction(e -> {
            System.out.println("button clicked");
        });

        Label tambahLabel = new Label("TAMBAHKAN EVENT");
        tambahLabel.getStyleClass().add("logoColor");

        HBox circleButtonBox = new HBox(10, btnCircle, tambahLabel);
        circleButtonBox.setAlignment(Pos.CENTER);

        // horisontal untuk header atas
        HBox topContainer = new HBox(370, circleButtonBox, skillUpBox);
        topContainer.setAlignment(Pos.CENTER);
        topContainer.setPadding(new Insets(10, 20, 10, 20));

        // kotak putih besar 
        Rectangle whiteBox = new Rectangle();
        whiteBox.setFill(Color.WHITE);
        whiteBox.setWidth(680);
        whiteBox.setHeight(400);
        whiteBox.setArcWidth(20);
        whiteBox.setArcHeight(20);

        // Create a VBox to hold content inside the white box
        VBox contentBox = new VBox(10);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        // button title event 
        Button whiteBoxButton = new Button("Fill your title of event here");
        whiteBoxButton.getStyleClass().add("buttomColor");
        whiteBoxButton.setPrefHeight(35);
        whiteBoxButton.setPrefWidth(450);
        whiteBoxButton.setStyle("-fx-background-radius: 10;");
        contentBox.getChildren().add(whiteBoxButton);

        // Add buttons and image views to the white box
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(whiteBoxButton);
        AnchorPane.setTopAnchor(whiteBoxButton, 40.0);
        AnchorPane.setLeftAnchor(whiteBoxButton, 50.0);

        Image addGambar = new Image(getClass().getResourceAsStream("/image/addimage.jpeg"));
        ImageView imageAdd = new ImageView(addGambar);
        imageAdd.setFitHeight(50);
        imageAdd.setFitWidth(50);

        // Button gambar yang untuk add image 
        Button btnGambar = new Button();
        btnGambar.getStyleClass().add("buttomColor");
        btnGambar.setGraphic(imageAdd);
        btnGambar.setPrefHeight(260);
        btnGambar.setPrefWidth(280);

        AnchorPane anchorGambar = new AnchorPane();
        anchorGambar.getChildren().add(btnGambar);
        AnchorPane.setTopAnchor(btnGambar, 100.0);
        AnchorPane.setLeftAnchor(btnGambar, 50.0);

        // button add image yang di bawah 
        Button btnAddImage = new Button("Add Image");
        btnAddImage.getStyleClass().add("buttomColor");
        btnAddImage.setPrefHeight(30);
        btnAddImage.setPrefWidth(100);

        ImageView imageViewBook = new ImageView();
        imageViewBook.setFitHeight(200);
        imageViewBook.setPreserveRatio(true);
        VBox vBoxImage = new VBox(btnAddImage,imageViewBook);
        
        AnchorPane.setTopAnchor(btnAddImage, 370.0);
        AnchorPane.setLeftAnchor(btnAddImage, 130.0);
        anchorGambar.getChildren().add(btnAddImage);

        // button pelatihan atas kiri kanan 
        Button btnPelatihan = new Button("Pelatihan");
        btnPelatihan.getStyleClass().add("buttomSubmit");
        btnPelatihan.setPrefHeight(30);
        btnPelatihan.setPrefWidth(100);
        btnPelatihan.setOnAction(null);

        AnchorPane.setTopAnchor(btnPelatihan, 45.0);
        AnchorPane.setRightAnchor(btnPelatihan, 70.0);
        anchorGambar.getChildren().add(btnPelatihan);

        // button deskripsi 
        Button addDesc = new Button("Fill your event description here");
        addDesc.getStyleClass().add("buttomColor");
        addDesc.setPrefHeight(260);
        addDesc.setPrefWidth(300);

        AnchorPane.setTopAnchor(addDesc, 100.0);
        AnchorPane.setRightAnchor(addDesc, 70.0);
        anchorGambar.getChildren().add(addDesc);

        // Button submit yang di bawah deskripsi
        Button btnSubmit = new Button("SUBMIT");
        btnSubmit.getStyleClass().add("buttomSubmit");
        btnSubmit.setPrefHeight(30);
        btnSubmit.setPrefWidth(100);

        // Anchor the submit button below the description button
        AnchorPane.setTopAnchor(btnSubmit, 370.0);
        AnchorPane.setRightAnchor(btnSubmit, 170.0);
        anchorGambar.getChildren().add(btnSubmit);

        // Create a StackPane to overlay the whiteBox with the content
        StackPane whitePane = new StackPane();
        whitePane.getChildren().addAll(whiteBox, anchorPane, anchorGambar);
        StackPane.setAlignment(contentBox, Pos.CENTER);

        // Main layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("latar2");
        root.setTop(topContainer);
        root.setCenter(whitePane);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        // Event handler for btnAddImage
        btnAddImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageViewBook.setImage(image);
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
