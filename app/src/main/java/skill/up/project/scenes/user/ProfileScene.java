// package skill.up.project.scenes.user;

// import javafx.geometry.Insets;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.Region;
// import javafx.stage.Stage;
// import skill.up.project.controllers.UserController;
// import skill.up.project.models.User;
// import skill.up.project.utils.UIUtil;

// public class ProfileScene {
//     private Stage stage;

//     public ProfileScene(Stage stage) {
//         this.stage = stage;
//     }

//     public void show(int id) {
//         AnchorPane root = new AnchorPane();
//         root.getStyleClass().add("root2");

//         Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
//         rectangleHome.getStyleClass().add("rounded-rectangle");
//         root.getChildren().add(rectangleHome);

//         // Top bar
//         Label labelTitleHome = UIUtil.createLabel("PROFILE", 28, 18);
//         labelTitleHome.getStyleClass().add("label-title-home");

//         Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
//         labelTitleName.getStyleClass().add("label-title-home");

//         ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
//         imageViewSmallLogo.getStyleClass().add("image-skillup");

//         Button buttonHome = UIUtil.createButtonWithImage("/images/Home_fill.png", 15, 45, 50, 50);
//         Button buttonEvent = UIUtil.createButtonWithImage("/images/Book_fill.png", 18, 140, 45, 45);
//         buttonEvent.setOnAction(e -> {
//             WebinarScene webinarScene = new WebinarScene(stage);
//             webinarScene.show(id);
//         });
//         Button buttonProfile = UIUtil.createButtonWithImage("/images/User_fill.png", 18, 405, 45, 45);

//         // Mendapatkan data pengguna berdasarkan ID
//         User user = UserController.getUserById(id);
        
//         if (user == null) {
//             System.out.println("User tidak ditemukan");
//             return;
//         }

//         /* ==> INSTANCE LAYOUT START <== */
//         Label labelEmail = UIUtil.createLabel("Email", 147, 100);
//         labelEmail.getStyleClass().add("label-email");

//         TextField textFieldEmail = UIUtil.createTextField(user.getEmail(), 147, 120);
//         textFieldEmail.setEditable(false);
//         textFieldEmail.getStyleClass().add("text-field-register");

//         Label labelName = UIUtil.createLabel("Nama", 147, 160);
//         labelName.getStyleClass().add("label-email");

//         TextField textFieldName = UIUtil.createTextField(user.getName(), 147, 180);
//         textFieldName.getStyleClass().add("text-field-register");

//         Label labelPhoneNumber = UIUtil.createLabel("Nomor Telepon", 147, 220);
//         labelPhoneNumber.getStyleClass().add("label-email");

//         TextField textFieldPhoneNumber = UIUtil.createTextField(user.getPhoneNumber(), 147, 240);
//         textFieldPhoneNumber.getStyleClass().add("text-field-register");

//         Label labelAge = UIUtil.createLabel("Umur", 147, 280);
//         labelAge.getStyleClass().add("label-email");

//         TextField textFieldAge = UIUtil.createTextField(String.valueOf(user.getAge()), 147, 300);
//         textFieldAge.getStyleClass().add("text-field-register");

//         Button buttonUpdate = UIUtil.createButton("Perbarui", 243, 340);
//         buttonUpdate.getStyleClass().add("button-profile");

//         root.getChildren().addAll(labelTitleHome, labelTitleName, textFieldEmail, labelEmail, textFieldName, labelName,
//                 textFieldPhoneNumber, labelPhoneNumber, textFieldAge, labelAge, buttonUpdate, imageViewSmallLogo,
//                 buttonHome, buttonEvent, buttonProfile);
//         /* ==> INSTANCE LAYOUT END <== */

//         Scene scene = new Scene(root, 740, 480);
//         scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
//         stage.setScene(scene);
//     }
// }
package skill.up.project.scenes.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import skill.up.project.controllers.UserController;
import skill.up.project.models.User;


public class ProfileScene {
    private Stage stage;

    public ProfileScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        User user = UserController.getUserById(id);

        // Logo Skill Up kanan
        Image imageSkill = new Image(getClass().getResourceAsStream("/image/logoskill.jpeg"));
        ImageView skillView = new ImageView(imageSkill);
        skillView.setFitHeight(30);
        skillView.setFitWidth(30);

        Label labelSkill = new Label("Skill Up");
        labelSkill.getStyleClass().add("logoColor");

        HBox skillUpBox = new HBox(10, skillView, labelSkill);
        skillUpBox.setAlignment(Pos.CENTER);

        // tombol kembali circle jadi kotak kiri atas
        Image circleImage = new Image(getClass().getResourceAsStream("/image/bulatpanah.jpeg"));
        ImageView bulatView = new ImageView(circleImage);
        bulatView.setFitHeight(25);
        bulatView.setFitWidth(25);

        Button btnCircle = new Button();
        btnCircle.setGraphic(bulatView);
        btnCircle.getStyleClass().add("latar2");
        btnCircle.setOnAction(e -> {
            WebinarScene WebinarScene = new WebinarScene(stage);
            WebinarScene.show(id);
        });

        HBox circleButtonBox = new HBox(10, btnCircle);
        circleButtonBox.setAlignment(Pos.CENTER);

        // horisontal untuk header atas
        HBox topContainer = new HBox(540, circleButtonBox, skillUpBox);
        topContainer.setAlignment(Pos.CENTER);
        topContainer.setPadding(new Insets(10, 20, 10, 20));

        // kotak putih besar
        Rectangle whiteBox = new Rectangle();
        whiteBox.setFill(Color.WHITE);
        whiteBox.setWidth(680);
        whiteBox.setHeight(400);
        whiteBox.setArcWidth(20);
        whiteBox.setArcHeight(20);

        // Label untuk profil
        Label proLabel = new Label("Profile");
        proLabel.getStyleClass().add("label-profile");

        StackPane labelPro = new StackPane(proLabel);
        labelPro.setPadding(new Insets(25, 0, 0, 0));
        labelPro.setAlignment(Pos.TOP_CENTER);

        // TextField deskripsi
        TextField textNama = new TextField();
        textNama.setText(user.getName());
        textNama.getStyleClass().add("namedkk");
        textNama.setPromptText("Name");
        textNama.setPrefWidth(550); 
        textNama.setMaxWidth(550);

        TextField textEmail = new TextField();
        textEmail.setText(user.getEmail());
        textEmail.getStyleClass().add("namedkk");
        textEmail.setPromptText("Email");
        textEmail.setPrefWidth(550);
        textEmail.setMaxWidth(550);

        TextField textAge = new TextField();
        // textAge.setText(user.getAge());
        textAge.getStyleClass().add("namedkk");
        textAge.setPromptText("Age");
        textAge.setPrefWidth(550);
        textAge.setMaxWidth(550);

        TextField textMbti = new TextField();
        textMbti.setText(user.getMbtiResult());
        textMbti.getStyleClass().add("namedkk");
        textMbti.setPromptText("MBTI Result");
        textMbti.setPrefWidth(550);
        textMbti.setMaxWidth(550);

        TextField textRegis = new TextField();
        textRegis.setText(user.getRegisteredWebinar());
        textRegis.getStyleClass().add("namedkk");
        textRegis.setPromptText("Registered Webinar");
        textRegis.setPrefWidth(550);
        textRegis.setMaxWidth(550);

        Button btnSave = new Button("Save");
        btnSave.getStyleClass().add("buttomSubmit");
        btnSave.setPrefHeight(30);
        btnSave.setPrefWidth(100);
        btnSave.setOnAction(e -> {
            WebinarScene WebinarScene = new WebinarScene(stage);
            WebinarScene.show(id);
        });

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(btnSave);
        AnchorPane.setTopAnchor(btnSave, 370.0);
        AnchorPane.setRightAnchor(btnSave, 150.0);


        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPrefHeight(30);
        vbox.setPrefWidth(550); // Ensuring the VBox does not exceed this width
        vbox.getChildren().addAll(labelPro, textNama, textEmail, textAge, textMbti, textRegis);

        StackPane whiteLay = new StackPane();
        whiteLay.getChildren().addAll(whiteBox, vbox, anchorPane);
        whiteLay.setAlignment(Pos.CENTER);

        // Main layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("latar2");
        root.setTop(topContainer);
        root.setCenter(whiteLay);

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
