package skill.up.project.scenes.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skill.up.project.controllers.AdminController;
import skill.up.project.utils.UIUtil;

public class RegisterSceneAdmin {
    private Stage stage;

    public RegisterSceneAdmin(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Pane root = new Pane();
        root.getStyleClass().add("root1");

        /* ==> INSTANCE LAYOUT START <== */
        Label labelTitle = UIUtil.createLabel("Register", 497, 38);
        labelTitle.getStyleClass().add("label-title-login");

        Label labelHello = UIUtil.createLabel("Create your account", 465, 93);
        labelHello.getStyleClass().add("label-hello");

        Label labelName = UIUtil.createLabel("Name", 390, 129);
        labelName.getStyleClass().add("label-email");

        TextField textFieldName = UIUtil.createTextField("Masukkan nama", 373, 150);
        textFieldName.getStyleClass().add("text-field-login");

        Label labelEmail = UIUtil.createLabel("Email", 390, 207);
        labelEmail.getStyleClass().add("label-email");

        TextField textFieldEmail = UIUtil.createTextField("Masukkan email", 373, 228);
        textFieldEmail.getStyleClass().add("text-field-login");

        Label labelPassword = UIUtil.createLabel("Password", 390, 285);
        labelPassword.getStyleClass().add("label-password");

        PasswordField passwordField = UIUtil.createPasswordField("Masukkan password", 373, 306);
        passwordField.getStyleClass().add("text-field-login");

        Button buttonRegister = UIUtil.createButton("Register", 380, 400);
        buttonRegister.getStyleClass().add("button-login");

        Label labelStatus = UIUtil.createLabel("", 452, 353);
        labelStatus.getStyleClass().add("label-status");

        ImageView imageViewSkillup = UIUtil.createImageView("/images/login_photos.png", 300, 405, 39, 37);
        imageViewSkillup.getStyleClass().add("image-skillup");

        // Tambahkan semua elemen ke root
        root.getChildren().addAll(labelTitle, labelHello, labelName, textFieldName, labelEmail, textFieldEmail, labelPassword, passwordField, buttonRegister, labelStatus, imageViewSkillup);

        /* ==> INSTANCE LAYOUT END <== */

        /* ==> BUTTON ACTION START <== */
        buttonRegister.setOnAction(e -> {
            String name = textFieldName.getText();
            String email = textFieldEmail.getText();
            String password = passwordField.getText();
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                labelStatus.setText("Nama, email, dan password harus diisi!");
                return;
            }

            boolean registered = AdminController.register(name, email, password);
            if (registered) {
                labelStatus.setText("Registrasi berhasil!");
            } else {
                labelStatus.setText("Registrasi gagal, coba lagi!");
            }
        });
        /* ==> BUTTON ACTION END <== */

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
