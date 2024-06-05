package skill.up.project.scenes.user;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import skill.up.project.controllers.AdminController;
import skill.up.project.controllers.UserController;
import skill.up.project.models.User;
import skill.up.project.scenes.LandingScene;
import skill.up.project.utils.UIUtil;

public class ProfileScene {
    private Stage stage;

    public ProfileScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("root2");

        Region rectangleHome = UIUtil.createRoundedRectangle(621, 412, 99, 55);
        rectangleHome.getStyleClass().add("rounded-rectangle");
        root.getChildren().add(rectangleHome);

        // Top bar
        Label labelTitleHome = UIUtil.createLabel("PROFILE", 28, 18);
        labelTitleHome.getStyleClass().add("label-title-home");

        Label labelTitleName = UIUtil.createLabel("SkillUp", 664, 18);
        labelTitleName.getStyleClass().add("label-title-home");

        ImageView imageViewSmallLogo = UIUtil.createImageView("/images/logoRadius.png", 21, 21, 638, 18);
        imageViewSmallLogo.getStyleClass().add("image-skillup");

        Button buttonHome = UIUtil.createButtonWithImage("/images/Home_fill.png", 15, 45, 50, 50);
        //TODO (selesai) saya tambahkan setOnactionnya
        buttonHome.setOnAction(e -> {
            HomeTest homeTest = new HomeTest(stage);
            homeTest.show(id);
        });

        Button buttonEvent = UIUtil.createButtonWithImage("/images/Book_fill.png", 18, 140, 45, 45);
        buttonEvent.setOnAction(e -> {
            WebinarScene webinarScene = new WebinarScene(stage);
            webinarScene.show(id);
        });
        Button buttonProfile = UIUtil.createButtonWithImage("/images/User_fill.png", 18, 405, 45, 45);

        // Mendapatkan data pengguna berdasarkan ID
        User user = UserController.getUserById(id);
        
        if (user == null) {
            System.out.println("User tidak ditemukan");
            return;
        }

        /* ==> INSTANCE LAYOUT START <== */
        Label labelEmail = UIUtil.createLabel("Email", 147, 100);
        labelEmail.getStyleClass().add("label-email");

        TextField textFieldEmail = UIUtil.createTextField(user.getEmail(), 147, 123);
        textFieldEmail.setEditable(false);
        textFieldEmail.getStyleClass().add("text-field-register");

        Label labelName = UIUtil.createLabel("Nama", 147, 173);
        labelName.getStyleClass().add("label-email");

        TextField textFieldName = UIUtil.createTextField(user.getName(), 147, 195);
        textFieldName.getStyleClass().add("text-field-register");

        Label labelPhoneNumber = UIUtil.createLabel("Nomor Telepon", 147, 249);
        labelPhoneNumber.getStyleClass().add("label-email");

        TextField textFieldPhoneNumber = UIUtil.createTextField(user.getPhoneNumber(), 147, 271);
        textFieldPhoneNumber.getStyleClass().add("text-field-register");

        Label labelAge = UIUtil.createLabel("Umur", 147, 325);
        labelAge.getStyleClass().add("label-email");

        TextField textFieldAge = UIUtil.createTextField(String.valueOf(user.getAge()), 147, 347);
        textFieldAge.getStyleClass().add("text-field-register");

        Label labelMbti = UIUtil.createLabel(user.getMbtiResult(), 508, 100);
        labelMbti.getStyleClass().add("label-mbti");
        labelMbti.getStyleClass().add("text-field-mbti");

        TextField textFieldBorder = UIUtil.createTextField(user.getRegisteredWebinar(), 508, 213);
        textFieldBorder.getStyleClass().add("text-field-border");

        //TODO ganti  menjadi Logout.png
        Button buttonLogout = UIUtil.createButtonWithImage("images/Logout2.jpg", 568, 412, 120, 40);
        buttonLogout.setOnAction(e -> {
            LandingScene landingScene = new LandingScene(stage);
            landingScene.show();
        });

        Button buttonUpdate = UIUtil.createButton("PERBARUI", 234, 414);
        buttonUpdate.getStyleClass().add("button-profile");
        buttonUpdate.setOnAction(e -> {
            String updatedName = textFieldName.getText();
            String updatedPhoneNumber = textFieldPhoneNumber.getText();
            int updatedAge = Integer.parseInt(textFieldAge.getText());

            boolean isUpdated = UserController.updateUser(id, updatedName, updatedPhoneNumber, updatedAge);

            if (isUpdated) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Failed to update profile.");
            }
        });

        root.getChildren().addAll(labelTitleHome, labelTitleName, textFieldEmail, labelEmail, textFieldName, labelName,
                textFieldPhoneNumber, labelPhoneNumber, textFieldAge, labelAge, labelMbti, textFieldBorder, buttonUpdate, imageViewSmallLogo,
                buttonHome, buttonEvent, buttonLogout, buttonProfile);
        /* ==> INSTANCE LAYOUT END <== */

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
