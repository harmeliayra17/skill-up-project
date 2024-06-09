package skill.up.project.scenes.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import skill.up.project.controllers.UserController;
import skill.up.project.models.User;
// import skill.up.project.controllers.AdminController;
// import skill.up.project.models.Admin;
import skill.up.project.scenes.LandingScene;
import skill.up.project.scenes.popUp.PopUpOutAdmin;
import skill.up.project.utils.UIUtil;

public class ProfileAdminScene {
    private Stage stage;

    public ProfileAdminScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int id) {
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("root3");

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

        Button buttonWebinars = UIUtil.createButtonWithImage("/images/Book_fill.png", 15, 45, 50, 50);
        buttonWebinars.setOnAction(e -> {
            HomeAdminScene homeAdminScene = new HomeAdminScene(stage);
            homeAdminScene.show(id);
        });

        Button buttonArticle = UIUtil.createButtonWithImage("/images/Article1.png", 18, 140, 45, 45);
        buttonArticle.setOnAction(e -> {
            ArticleAdminScene articleScene = new ArticleAdminScene(stage);
            articleScene.show(id);
        });

        Button buttonProfile = UIUtil.createButtonWithImage("/images/Profile_Red.png", 18, 405, 45, 45);

        // Mendapatkan data pengguna berdasarkan ID
        User admin = UserController.getUserById(id);
        
        if (admin == null) {
            System.out.println("User tidak ditemukan");
            return;
        }

        /* ==> INSTANCE LAYOUT START <== */
        Label labelEmail = UIUtil.createLabel("Email", 164, 80);
        labelEmail.getStyleClass().add("label-email");

        TextField textFieldEmail = UIUtil.createTextField(admin.getEmail(), 164, 103);
        textFieldEmail.setEditable(false);
        textFieldEmail.getStyleClass().add("text-field-admin");

        Label labelName = UIUtil.createLabel("Nama", 164, 157);
        labelName.getStyleClass().add("label-email");

        TextField textFieldName = UIUtil.createTextField(admin.getName(), 164, 179);
        textFieldName.getStyleClass().add("text-field-admin");

        Label labelPhoneNumber = UIUtil.createLabel("Nomor Telepon", 164, 233); 
        labelPhoneNumber.getStyleClass().add("label-email");

        TextField textFieldPhoneNumber = UIUtil.createTextField(admin.getPhoneNumber(), 164, 255);
        textFieldPhoneNumber.getStyleClass().add("text-field-admin");

        Label labelCompany = UIUtil.createLabel("Company", 164, 309);
        labelCompany.getStyleClass().add("label-email");

        TextField textFieldCompany = UIUtil.createTextField(admin.getCompany(), 164, 331);
        textFieldCompany.getStyleClass().add("text-field-admin");

        Button buttonLogout = UIUtil.createButtonWithImage("images/Logout2.jpg", 568, 412, 120, 40);
        buttonLogout.setOnAction(e -> {
            PopUpOutAdmin popUpOutAdmin = new PopUpOutAdmin(stage);
            popUpOutAdmin.show(id);
        });

        Button buttonUpdate = UIUtil.createButton("PERBARUI", 333, 404);
        buttonUpdate.getStyleClass().add("button-profile");
        buttonUpdate.setOnAction(e -> {
            String updatedName = textFieldName.getText().trim();
            String updatedPhoneNumber = textFieldPhoneNumber.getText().trim();
            String updatedCompany = textFieldCompany.getText().trim();
        
            String currentName = admin.getName();
            String currentPhoneNumber = admin.getPhoneNumber();
            String currentCompany = admin.getCompany();
        
            boolean isUpdated = false;
        
            if (!updatedName.isEmpty() && !updatedName.equals(currentName)) {
                admin.setName(updatedName);
                isUpdated = true;
            }
        
            if (!updatedPhoneNumber.isEmpty() && !updatedPhoneNumber.equals(currentPhoneNumber)) {
                admin.setPhoneNumber(updatedPhoneNumber);
                isUpdated = true;
            }
        
            if (!updatedCompany.isEmpty() && !updatedCompany.equals(currentCompany)) {
                admin.setCompany(updatedCompany);
                isUpdated = true;
            }
        
            if (isUpdated) {
                boolean updateSuccess = UserController.updateAdmin(admin.getId(), admin.getName(), admin.getPhoneNumber(), admin.getCompany());
                if (updateSuccess) {
                    System.out.println("Profile updated successfully!");
                } else {
                    System.out.println("Failed to update profile.");
                }
            } else {
                System.out.println("No changes to update.");
            }
        });
        

        root.getChildren().addAll(labelTitleHome, labelTitleName, textFieldEmail, labelEmail, textFieldName, labelName,
                textFieldPhoneNumber, labelPhoneNumber, labelCompany, textFieldCompany, buttonUpdate, imageViewSmallLogo,
                buttonWebinars, buttonArticle, buttonLogout, buttonProfile);
        /* ==> INSTANCE LAYOUT END <== */

        Scene scene = new Scene(root, 740, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        stage.setScene(scene);
    }
}
