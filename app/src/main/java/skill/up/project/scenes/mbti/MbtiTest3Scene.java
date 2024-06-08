package skill.up.project.scenes.mbti;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import skill.up.project.models.mbtiPackage.MBTI;
import skill.up.project.models.mbtiPackage.ThinkVsFeel;

public class MbtiTest3Scene {
    private Stage stage;
    private String resultBefore;
    private String resultTvsF = "";
    private ArrayList<String> answers = new ArrayList<>();
    MBTI TvsF = new ThinkVsFeel();
    ThinkVsFeel thinkVsFeel = new ThinkVsFeel();

    public MbtiTest3Scene(Stage stage) {
        this.stage = stage;
    }

    private String[] questions = {
        "19. Ketika berhadapan dengan masalah, Anda lebih cenderung:",
        "20. Anda lebih suka mengambil keputusan berdasarkan:",
        "21. Anda lebih sering:",
        "22. Dalam membuat keputusan, Anda lebih:",
        "23. Saat menghadapi konflik, Anda lebih:",
        "24. Anda lebih sering membuat keputusan berdasarkan:",
        "25. Anda lebih sering:",
        "26. Saat bekerja dengan orang lain, Anda lebih:",
        "27. Anda lebih sering:",
    };

    private String[] buttonAtext = {
        "A) Menganalisis dan mencari solusi logis",
        "A) Logika dan analisis",
        "A) Mengandalkan logika dalam menyelesaikan masalah",
        "A) Menggunakan kepala Anda",
        "A) Mencari solusi logis",
        "A) Fakta dan analisis",
        "A) Mengambil tindakan berdasarkan logika",
        "A) Terfokus pada tugas dan tujuan",
        "A) Memberikan kritik yang jujur meski bisa melukai perasaan",
    };

    private String[] buttonBtext = {
        "B) Mempertimbangkan perasaan semua orang yang terlibat",
        "B) Nilai pribadi dan perasaan",
        "B) Mengandalkan intuisi dan perasaan dalam menyelesaikan masalah",
        "B) Menggunakan hati Anda",
        "B) Mencari solusi yang harmonis untuk semua pihak",
        "B) Perasaan dan intuisi ",
        "B) Mengambil tindakan berdasarkan perasaan",
        "B) Terfokus pada dinamika dan hubungan tim ",
        "B) Menghindari kritik langsung untuk menjaga perasaan",
    };

    public void show(int id) {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setBackground(new Background(new BackgroundFill(Color.web("#7D1935"), CornerRadii.EMPTY, Insets.EMPTY)));
        mainLayout.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(25), Insets.EMPTY)));

        stackPane.setPrefSize(694, 412);
        stackPane.setMinSize(694, 412);
        stackPane.setMaxSize(694, 412);

        Label labelLogo = new Label("SkillUp");
        labelLogo.getStyleClass().add("labelLogo-style");

        Label labelMbti = new Label("MBTI Test");
        labelMbti.getStyleClass().add("labelLogo-style");

        ImageView logo = new ImageView();
        logo.setFitWidth(21);
        logo.setFitHeight(21);
        logo.setPreserveRatio(true);
        Image chooseLogo = new Image(getClass().getResourceAsStream("/images/LogoRadius.png"));
        logo.setImage(chooseLogo);

        HBox logoText = new HBox(5, logo, labelLogo);

        HBox header = new HBox(530, labelMbti, logoText);
        header.setAlignment(Pos.CENTER);
        BorderPane.setMargin(header, new Insets(10, 0, 0, 0));

        VBox vBoxQuestions = new VBox();

        Map<Integer, Button> lastPressedButtonMap = new HashMap<>(); //

        for (int i = 0; i < questions.length; i++) {
            VBox answerButtons = new VBox(5);
            answerButtons.setAlignment(Pos.CENTER);

            VBox perQuestion = new VBox(10);
            perQuestion.setAlignment(Pos.CENTER);

            Label questionLabel = new Label(questions[i]);
            questionLabel.getStyleClass().add("labelQuestion-style");

            Button buttonA = new Button(buttonAtext[i]);
            buttonA.getStyleClass().add("Abutton-style");
            buttonA.setWrapText(true);

            Button buttonB = new Button(buttonBtext[i]);
            buttonB.getStyleClass().add("Bbutton-style");
            buttonB.setWrapText(true);

            int questionIndex = i;

            buttonA.setOnAction(e -> {
                addAnswer("A", questionIndex);
                updateButtonStyles(lastPressedButtonMap, questionIndex, buttonA, buttonB);
            });
            
            buttonB.setOnAction(e -> {
                addAnswer("B", questionIndex);
                updateButtonStyles(lastPressedButtonMap, questionIndex, buttonB, buttonA);
            });

            answerButtons.getChildren().addAll(buttonA, buttonB);
            perQuestion.getChildren().addAll(questionLabel, answerButtons);
            vBoxQuestions.getChildren().addAll(perQuestion);
        }
        vBoxQuestions.setSpacing(40);
        vBoxQuestions.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20);
        HBox hBox = new HBox(50);

        Label labelInfo = new Label("");
        labelInfo.setPrefWidth(420);
        labelInfo.setAlignment(Pos.CENTER);
        labelInfo.getStyleClass().add("labelInfo-style");

        Button buttonnext = new Button("Next");
        buttonnext.getStyleClass().add("backNextButton-style");
        buttonnext.setOnAction(e -> {
            boolean allAnswered = true;
            for (String answer : answers) {
                if (answer == null) {
                    allAnswered = false;
                    break;
                }
            }
            if (allAnswered && answers.size() == questions.length) {
                handleAnswer();
                MbtiTest4Scene mbtiTest4Scene = new MbtiTest4Scene(stage);
                resultBefore += resultTvsF;
                mbtiTest4Scene.setResultBefore(resultBefore);
                mbtiTest4Scene.show(id);
            } else {
                labelInfo.setText("Pastikan semua pertanyaan telah terjawab.");
            }
        });

        hBox.getChildren().addAll(labelInfo, buttonnext);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        vBox.getChildren().addAll(vBoxQuestions, hBox);
        
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.getStyleClass().add("scrollPane-style");
        scrollPane.setFitToWidth(true); 
        scrollPane.setFitToHeight(true);

        stackPane.getChildren().addAll(scrollPane);

        mainLayout.setTop(header);
        mainLayout.setCenter(stackPane);
        stage.getScene().setRoot(mainLayout);
    }


    public void addAnswer(String answer, int questionIndex) {
        while (answers.size() <= questionIndex) {
            answers.add(null);
        }
        answers.set(questionIndex, answer);
    }

    public void handleAnswer() {
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals("A")) {
                thinkVsFeel.addThinking();
            } else {
                thinkVsFeel.addFeeling();
            }
        }
        thinkVsFeel.mbtiResult();
        TvsF = thinkVsFeel;
        resultTvsF = TvsF.getResultTvsF();
        setResultTvsF(resultTvsF);
    }

    private void updateButtonStyles(Map<Integer, Button> lastPressedButtonMap, int questionIndex, Button pressedButton, Button otherButton) {
        // Reset the style of the previously pressed button
        if (lastPressedButtonMap.containsKey(questionIndex)) {
            lastPressedButtonMap.get(questionIndex).getStyleClass().removeAll("AbuttonClicked-style", "BbuttonClicked-style");
            lastPressedButtonMap.get(questionIndex).getStyleClass().add(lastPressedButtonMap.get(questionIndex).getText().startsWith("A") ? "Abutton-style" : "Bbutton-style");
        }
    
        // Apply the clicked style to the pressed button
        pressedButton.getStyleClass().removeAll("Abutton-style", "Bbutton-style");
        pressedButton.getStyleClass().add(pressedButton.getText().startsWith("A") ? "AbuttonClicked-style" : "BbuttonClicked-style");
    
        // Update the map with the newly pressed button
        lastPressedButtonMap.put(questionIndex, pressedButton);
    }

    public String getResultTvsF() {
        return resultTvsF;
    }
    public void setResultTvsF(String resultTvsF) {
        this.resultTvsF = resultTvsF;
    }
    public String getResultBefore() {
        return resultBefore;
    }
    public void setResultBefore(String resultBefore) {
        this.resultBefore = resultBefore;
    }
}


