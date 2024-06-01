package skill.up.project.scenes.user;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skill.up.project.controllers.UserController;
import skill.up.project.models.User;
import skill.up.project.utils.UIUtil;

public class LoginScene {
    private Stage stage;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Pane root = new Pane();
        root.getStyleClass().add("root1");

        /* ==> INSTANCE LAYOUT START <== */
        Label labelTitle = UIUtil.createLabel("Login", 497, 38);
        labelTitle.getStyleClass().add("label-title-login");

        Label labelHello = UIUtil.createLabel("Sign in to your account", 465, 93);
        labelHello.getStyleClass().add("label-hello");

        Label labelEmail = UIUtil.createLabel("Email", 390, 129);
        labelEmail.getStyleClass().add("label-email");

        TextField textFieldEmail = UIUtil.createTextField("Masukkan email", 373, 150);
        textFieldEmail.getStyleClass().add("text-field-login");

        Label labelPassword = UIUtil.createLabel("Password", 390, 207);
        labelPassword.getStyleClass().add("label-password");

        PasswordField passwordField = UIUtil.createPasswordField("Masukkan password", 373, 228);
        passwordField.getStyleClass().add("text-field-login");

        Button buttonLogin = UIUtil.createButton("Login", 380, 400);
        buttonLogin.getStyleClass().add("button-login");
        
        Button buttonRegister = UIUtil.createButton("Register", 556, 400);
        buttonRegister.getStyleClass().add("button-register");        

        Label labelStatus = UIUtil.createLabel("", 452, 353);
        labelStatus.getStyleClass().add("label-status");

        ImageView imageViewSkillup = UIUtil.createImageView("/images/login_photos.png", 300, 405, 39, 37);
        imageViewSkillup.getStyleClass().add("image-skillup");

        // Tambahkan buttonLogin, buttonRegister, dan labelStatus ke dalam buttonBox
        HBox buttonBox = new HBox(34); // Jarak antar tombol 
        buttonBox.getChildren().addAll(buttonLogin, buttonRegister);
        buttonBox.setLayoutX(380); // Penyesuaian posisi X
        buttonBox.setLayoutY(400); // Penyesuaian posisi Y

        // Tambahkan semua elemen ke root
        root.getChildren().addAll(labelTitle, labelHello, labelEmail, textFieldEmail, labelPassword, passwordField, labelStatus, imageViewSkillup, buttonBox);

        /* ==> INSTANCE LAYOUT END <== */

        /* ==> BUTTON ACTION START <== */
        buttonLogin.setOnAction(e -> {
            String email = textFieldEmail.getText();
            String password = passwordField.getText();
            if (email.isEmpty() || password.isEmpty()) {
                labelStatus.setText("Email dan password harus diisi!");
                return;
            }
            
            User user = UserController.login(email, password);
            if (user != null) {
                int id = user.getId();
                HomeTest homeTest = new HomeTest(stage);
                homeTest.show(id);
            } else {
                if (UserController.isUserExists(email)) {
                    // Jika email terdaftar tapi password salah
                    labelStatus.setText("Password salah!");
                } else {
                    // Jika email belum terdaftar
                    labelStatus.setText("Email belum terdaftar, silahkan registrasi!");
                }
            }
        });

        buttonRegister.setOnAction(e -> {
            // Buat instance RegisterScene dengan memasukkan stage yang sama
            RegisterScene registerScene = new RegisterScene(stage);
            
            // Panggil metode show() untuk menampilkan RegisterScene
            registerScene.show();
        });
        /* ==> BUTTON ACTION END <== */

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
