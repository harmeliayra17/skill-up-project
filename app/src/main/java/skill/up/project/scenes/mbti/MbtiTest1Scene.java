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
import skill.up.project.models.mbtiPackage.ExtraVsIntro;
import skill.up.project.models.mbtiPackage.MBTI;
import skill.up.project.scenes.user.HomeTest;

public class MbtiTest1Scene {
    private Stage stage;
    private String resultEvsI = "";
    private ArrayList<String> answers = new ArrayList<>();

    MBTI EvsI = new ExtraVsIntro();
    ExtraVsIntro extraVsIntro = new ExtraVsIntro();

    public MbtiTest1Scene(Stage stage) {
        this.stage = stage;
    }

    private String[] questions = {
        "1. Anda lebih suka", 
        "2. Dalam situasi sosial, Anda lebih sering:",
        "3. Anda merasa lebih energik setelah:",
        "4. Anda lebih suka:",
        "5. Saat menghadiri acara sosial, Anda lebih:",
        "6. Di tempat kampus atau sekolah, Anda lebih suka:",
        "7. Saat liburan, Anda lebih memilih:",
        "8. Anda lebih sering:",
        "9. Saat berkomunikasi, Anda lebih suka:",
    };

    private String[] buttonAtext = {
        "A) Menghabiskan waktu bersama orang banyak",
        "A) Memulai percakapan",
        "A) Berinteraksi dengan banyak orang",
        "A) Berinteraksi dalam kelompok besar",
        "A) Mencari kesempatan untuk bertemu orang baru",
        "A) Berkolaborasi dengan banyak orang",
        "A) Berlibur dengan banyak teman",
        "A) Menikmati keramaian dan aktivitas sosial",
        "A) Berbicara dengan banyak orang secara langsung",
    };

    private String[] buttonBtext = {
        "B) Menghabiskan waktu sendirian atau dengan beberapa teman dekat",
        "B) Menunggu orang lain yang memulai",
        "B) Menghabiskan waktu sendiri",
        "B) Berinteraksi dalam kelompok kecil atau individu",
        "B) Tetap bersama dengan orang yang Anda kenal",
        "B) Fokus belajar sendiri atau dengan satu orang dekat", 
        "B) Berlibur sendirian atau dengan satu teman dekat",
        "B) Menghindari keramaian dan mencari ketenangan",
        "B) Menulis atau berbicara dengan beberapa orang saja",
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

        Button buttonHome = new Button("Home");
        buttonHome.getStyleClass().add("backNextButton-style");
        buttonHome.setOnAction(e -> {
            HomeTest homeTest = new HomeTest(stage);
            homeTest.show(id);
        });

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
                MbtiTest2Scene mbtiTest2Scene = new MbtiTest2Scene(stage);
                mbtiTest2Scene.setResultBefore(resultEvsI);
                mbtiTest2Scene.show(id);
            } else {
                labelInfo.setText("Pastikan semua pertanyaan telah terjawab.");
            }
        });

        hBox.getChildren().addAll(buttonHome, labelInfo, buttonnext);
        hBox.setAlignment(Pos.BOTTOM_CENTER);

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
                extraVsIntro.addExtraversion();
            } else {
                extraVsIntro.addIntroversion();
            }
        }
        extraVsIntro.mbtiResult();
        EvsI = extraVsIntro;
        resultEvsI = EvsI.getResultEvsI();
        setResultEvsI(resultEvsI);
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

    public String getResultEvsI() {
        return resultEvsI;
    }
    public void setResultEvsI(String resultEvsI) {
        this.resultEvsI = resultEvsI;
    }  
}