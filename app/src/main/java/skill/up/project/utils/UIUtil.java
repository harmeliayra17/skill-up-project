package skill.up.project.utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class UIUtil {

    public static Label createLabel(String text, double x, double y) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    public static TextField createEmailField(String promptText, double x, double y) {
        TextField emailField = new TextField();
        emailField.setPromptText(promptText);
        emailField.setLayoutX(x);
        emailField.setLayoutY(y);
        return emailField;
    }

    public static PasswordField createPasswordField(String promptText, double x, double y) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setLayoutX(x);
        passwordField.setLayoutY(y);
        return passwordField;
    }

    public static TextField createTextField(String promptText, double x, double y) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        return textField;
    }

    public static Text createText(String content, double x, double y) {
        Text text = new Text(content);
        text.setLayoutX(x);
        text.setLayoutY(y);
        return text;
    }

    public static Button createButton(String text, double x, double y) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    public static ImageView createImageView(String imagePath, double fitWidth, double fitHeight, double x, double y) {
        Image image = new Image(UIUtil.class.getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        return imageView;
    }

    public static Region createRoundedRectangle(double width, double height, double x, double y) {
        Region roundedRect = new Region();
        roundedRect.setLayoutX(x);
        roundedRect.setLayoutY(y);
        roundedRect.setPrefSize(width, height);
        roundedRect.getStyleClass().add("rounded-rectangle");
        return roundedRect;
    }

    public static Button createButtonWithImage(String imagePath, double layoutX, double layoutY, double width, double height) {
        Button button = new Button(); 
        ImageView imageView = new ImageView(new Image(imagePath));
        button.setLayoutX(layoutX); 
        button.setLayoutY(layoutY); 

        imageView.setFitWidth(width); 
        imageView.setFitHeight(height); 
        button.setGraphic(imageView); 
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;"); // Menghapus latar belakang dan border
        return button;
    }

    public static BackgroundImage createBackgroundImage(String imagePath, double width, double height) {
        Image image = new Image(imagePath, width, height, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );
        return backgroundImage;
    }
}
