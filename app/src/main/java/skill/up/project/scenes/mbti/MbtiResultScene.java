package skill.up.project.scenes.mbti;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import skill.up.project.controllers.UserController;
import skill.up.project.scenes.user.HomeTest;

public class MbtiResultScene {
    private Stage stage;
    private String result;

    public MbtiResultScene(Stage stage) {
        this.stage = stage;
    }

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

        Text text = new Text("Hasil MBTI kamu: ");
        text.getStyleClass().add("textResult-style");
        
        Text resultText = new Text(result);
        resultText.getStyleClass().add("textResult-style");
        HBox resultHBox = new HBox(text, resultText);

        Label descriptionLabel = new Label(resultDescription(result));
        descriptionLabel.getStyleClass().add("labelDescription-style");
        descriptionLabel.setPrefWidth(400);
        descriptionLabel.setPrefHeight(350);
        descriptionLabel.setAlignment(Pos.CENTER);

        ImageView imageViewResult = new ImageView(resultImage(result));
        imageViewResult.setFitHeight(400);
        imageViewResult.setFitWidth(230);
        imageViewResult.setPreserveRatio(true);

        Label labelInfo = new Label("");
        labelInfo.setPrefWidth(420);
        labelInfo.setAlignment(Pos.CENTER);
        labelInfo.getStyleClass().add("labelInfo-style");

        Button backButton = new Button("  Home  ");
        backButton.getStyleClass().add("backNextButton-style");
        backButton.setOnAction(e -> {
            if (UserController.updateUserMbtiResult(id, result)) {
                HomeTest homeTest = new HomeTest(stage);
                homeTest.show(id);
            } else {
                //TODO handle elsenya
                labelInfo.setText("hmmm... eror:(");
            }
        });

        HBox hBox = new HBox(30);
        HBox.setMargin(imageViewResult, new Insets(20, 0, 0, 20));
        HBox.setMargin(descriptionLabel, new Insets(20,0,0,0));

        hBox.getChildren().addAll(imageViewResult, descriptionLabel);

        VBox resultvBox = new VBox();
        resultvBox.setAlignment(Pos.CENTER);
        VBox.setMargin(resultHBox, new Insets(10, 0, 0, 200)); 
        VBox.setMargin(labelInfo, new Insets(0,0,15,0));
        VBox.setMargin(backButton, new Insets(0,0,10,0)); 

        resultvBox.getChildren().addAll(resultHBox, hBox, labelInfo, backButton);

        stackPane.getChildren().addAll(resultvBox);

        mainLayout.setTop(header);
        mainLayout.setCenter(stackPane);
        stage.getScene().setRoot(mainLayout);
    }

    public Image resultImage(String result) {
        if (result.equals("INFP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/INFP.png"));
            return image;
        } else if (result.equals("INTJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/INTJ.png"));
            return image;
        } else if (result.equals("INFJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/INFJ.png"));
            return image;
        } else if (result.equals("INTP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/INTP.png"));
            return image;
        } else if (result.equals("ENTJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ENTJ.png"));
            return image;
        } else if (result.equals("ENTP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ENTP.png"));
            return image;
        } else if (result.equals("ENFJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ENFJ.png"));
            return image;
        } else if (result.equals("ENFP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ENFP.png"));
            return image;
        } else if (result.equals("ISFJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ISFJ.png"));
            return image;
        } else if (result.equals("ISFP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ISFP.png"));
            return image;
        } else if (result.equals("ISTJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ISTJ.png"));
            return image;
        } else if (result.equals("ISTP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ISTP.png"));
            return image;
        } else if (result.equals("ESFJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ESFJ.png"));
            return image;
        } else if (result.equals("ESFP")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ESFP.png"));
            return image;
        } else if (result.equals("ESTJ")) {
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ESTJ.png"));
            return image;
        } else { //ESTP
            Image image = new Image(getClass().getResourceAsStream("/mbtiImages/ESTP.png"));
            return image;
        }
    }
    public String resultDescription(String result) {
        if (result.equals("INFP")) {
            return "INFP adalah seseorang yang idealis, teguh \n" + 
                        "memegang prinsip, dan setia terutama pada orang-\n" + 
                        "orang penting dalam hidupnya. Tipe kepribadian MBTI \n" + 
                        "ini memiliki rasa ingin tahu yang tinggi, terbuka dengan \n" + 
                        "berbagai kemungkinan. Ia adalah sosok yang fleksibel \n" + 
                        "dan adaptif, kecuali pada prinsip yang dipegangnya. \n" + 
                        "Seorang idealis dan penuh empati, sering tertarik pada \n" + 
                        "pekerjaan yang melibatkan kreativitas dan advokasi, \n" + 
                        "seperti penulis, seniman, psikolog, konselor, guru, aktivis, \n" + 
                        "dan pekerja sosial.";
        } else if (result.equals("INTJ")) {
            return "INTJ adalah orang-orang yang dapat dengan cepat \n" + 
                        "memahami pola atas suatu peristiwa yang tengah \n" + 
                        "terjadi, kemudian menyusun perspektif dalam jangka \n" + 
                        "panjang. Mereka mandiri, terorganisir, serta memiliki \n" + 
                        "standar kompetensi dan kinerja yang tinggi untuk diri \n" + 
                        "sendiri dan orang lain. INTJ sering unggul dalam \n" + 
                        "pekerjaan yang membutuhkan perencanaan dan \n" + 
                        "pemecahan masalah yang kompleks, seperti ilmuwan, \n" + 
                        "insinyur, pengembang perangkat lunak, analis strategis, \n" + 
                        "manajer proyek, pengacara, dan arsitek.";
        } else if (result.equals("INFJ")) {
            return "INFJ adalah si pencari makna. Ia tertarik untuk \n" + 
                        "memahami pemikiran orang lain, hubungan antar ide, \n" + 
                        "bahkan hubungan sosial. Punya komitmen tinggi \n" + 
                        "dalam bekerja, tipe kepribadian MBTI ini sering tertarik \n" + 
                        "pada pekerjaan yang memungkinkan mereka \n" + 
                        "membantu orang lain dan mengejar visi mereka, \n" + 
                        "seperti psikolog, konselor, penulis, pengacara, guru, \n" + 
                        "pekerja sosial, dan aktivis.";
        } else if (result.equals("INTP")) {
            return "Tipe kepribadian MBTI ini lebih tertarik dengan ide \n" + 
                        "ketimbang interaksi sosial. Mereka adalah orang-orang \n" + 
                        "yang teoretis dan abstrak, dengan tampilan yang \n" + 
                        "cerdas dan tenang. Jika memiliki minat, mereka \n" + 
                        "mampu fokus mendalami suatu masalah sampai \n" + 
                        "menemukan solusi. Seorang INTP menikmati pekerjaan \n" + 
                        "yang menantang secara intelektual dan membutuhkan \n" + 
                        "analisis mendalam, seperti ilmuwan, pengembang \n" + 
                        "perangkat lunak, peneliti, ahli matematika, profesor, \n" + 
                        "insinyur sistem, dan konsultan teknologi.";
        } else if (result.equals("ENTJ")) {
            return "Berkat pengetahuannya yang luas, ENTJ adalah \n" + 
                        "pembaca keadaan yang baik. Ia peka dalam memilah \n" + 
                        "prosedur atau kebijakan yang kurang efisien, bahkan \n" + 
                        "mampu mengembangkan sistem guna mengatasi \n" + 
                        "persoalan dalam organisasi. Tipe kepribadian MBTI ini \n" + 
                        "tak segan memaksakan idenya. Mereka unggul dalam \n" + 
                        "peran kepemimpinan dan strategi, seperti CEO, \n" + 
                        "manajer proyek, pengacara, insinyur, konsultan bisnis, \n" + 
                        "profesor, dan analis strategis.";
        } else if (result.equals("ENTP")) {
            return "Sisi ekstrovert kepribadian MBTI ini menjadikannya \n" +                                
                        "sosok yang blak-blakan. Mereka tak ragu bila harus \n" + 
                        "memecahkan masalah dengan cara-cara yang \n" + 
                        "menantang, namun sekaligus strategis. Tipe ENTP \n" + 
                        "menyukai kegiatan-kegiatan yang menarik dan mudah \n" + 
                        "merasa jenuh dengan rutinitas. ENTP unggul dalam \n" + 
                        "pekerjaan yang membutuhkan debat dan inovasi, \n" + 
                        "seperti pengacara, pengusaha, konsultan, jurnalis, \n" + 
                        "pemasar, pengembang produk, dan pengembang \n" + 
                        "perangkat lunak.";
        } else if (result.equals("ENFJ")) {
            return "ENFJ adalah sosok yang hangat, berempati tinggi, \n" + 
                        "dan pendengar yang baik. Tipe kepribadian MBTI ini \n" + 
                        "senang bergaul, suka memudahkan urusan dan \n" + 
                        "mendorong orang lain mencapai potensinya. Ia \n" + 
                        "mampu menerima kritik dan pujian dengan baik. ENFJ \n" + 
                        "sukses dalam peran yang melibatkan pengaruh dan \n" + 
                        "pengajaran, seperti konselor, pengajar, manajer \n" + 
                        "sumber daya manusia, aktivis, penulis, pembicara \n" + 
                        "motivasi, dan pekerja sosial.";
        } else if (result.equals("ENFP")) {
            return "ENFP adalah seorang berkarakter hangat. Ia adalah \n" + 
                        "sosok yang imajinatif dengan antusiasme tinggi. \n" + 
                        "Kemampuannya dalam memahami pola dan \n" + 
                        "hubungan suatu informasi dengan kejadian tertentu \n" + 
                        "membuat ENFP percaya diri dalam melakukan sesuatu. \n" + 
                        "Tipe kepribadian MBTI ini suportif, fleksibel, spontan, dan \n" + 
                        "fasih berbicara. Seorang yang kreatif dan penuh \n" + 
                        "semangat, mereka cocok dalam pekerjaan yang \n" + 
                        "memungkinkan mereka berinovasi dan berinteraksi \n" + 
                        "dengan orang lain, seperti penulis, jurnalis, konselor, \n" + 
                        "pekerja sosial, pengajar, public relations, dan \n" + 
                        "pengembang kreatif.";
        } else if (result.equals("ISFJ")) {
            return "ISFJ itu tenang, teliti, bertanggungjawab, \n" + 
                        "berkomitmen, telaten, cermat, baik hati, loyal, dan \n" + 
                        "perhatian. Sesuatu yang penting akan diingatnya \n" + 
                        "secara spesifik. Kepribadian MBTI ini menyukai \n" + 
                        "ketertiban di tempat tinggal maupun tempat kerja.  \n" + 
                        "Dengan sifat mereka yang penuh perhatian, praktis, \n" + 
                        "dan berdedikasi, pekerjaan yang sesuai meliputi \n" + 
                        "perawat, pekerja sosial, guru, konselor, administrator, \n" + 
                        "perpustakawan, dan spesialis sumber daya manusia, di \n" + 
                        "mana mereka bisa menunjukkan kepedulian dan \n" + 
                        "kepraktisan.";
        } else if (result.equals("ISFP")) {
            return "Kepribadian MBTI ISFP adalah sosok yang tenang, \n" + 
                        "sensitif, dan baik hati. Mereka membutuhkan ruang \n" + 
                        "sendiri, bekerja sesuai dengan waktunya sendiri, hadir \n" + 
                        "dan menikmati masa kini. Mereka berkomitmen pada \n" + 
                        "orang atau prinsip yang penting bagi dirinya. Karena \n" + 
                        "tak menyukai perselisihan atau konflik, ISFP takkan \n" + 
                        "memaksakan pendapat atau prinsipnya. Tipe ISFP \n" + 
                        "menikmati pekerjaan yang memungkinkan mereka \n" + 
                        "mengekspresikan diri dan membantu orang lain secara \n" + 
                        "langsung, seperti desainer grafis, artis, fotografer, \n" + 
                        "penata rambut, perawat hewan, penjaga taman, dan \n" + 
                        "musisi.";
        } else if (result.equals("ISTJ")) {
            return "ISTJ berkarakter tenang, serius, teliti, tekun, handal, \n" + 
                        "realistis, praktis, dan logis. Orientasinya pada tanggung \n" + 
                        "jawab dan fakta, mengedepankan logika saat \n" + 
                        "memutuskan sesuatu. Ia menyukai pekerjaan dan \n" + 
                        "kehidupan yang tertib dan teratur. Tak heran bila sosok \n" + 
                        "ini loyal dan memegang teguh tradisi. Pekerjaan yang \n" + 
                        "cocok termasuk akuntan, auditor, manajer proyek, \n" + 
                        "analis data, hakim, pengacara, dan dokter, di mana \n" + 
                        "mereka bisa menerapkan keterampilan analitis dan \n" + 
                        "keandalan mereka.";
        } else if (result.equals("ISTP")) {
            return "Tipe kepribadian MBTI ini berkarakter toleran dan \n" + 
                        "fleksibel. Ketenangannya dalam menganalisis \n" + 
                        "membuatnya mampu bertindak cepat menemukan \n" + 
                        "solusi. Berminat pada hubungan sebab-akibat, ISTP \n" + 
                        "dapat mengolah fakta secara efisien dan logis. ISTP  \n" + 
                        "suka bekerja dengan tangan dan memecahkan \n" + 
                        "masalah teknis. Pekerjaan yang cocok termasuk \n" + 
                        "insinyur mekanik, teknisi, pilot, detektif, ahli bedah, dan \n" + 
                        "atlet professional.";
        } else if (result.equals("ESFJ")) {
            return "ESFJ adalah karakter yang suka bekerja sama dalam \n" + 
                        "lingkungan yang harmonis. Mereka mampu \n" + 
                        "memahami kebutuhan orang lain berusahan \n" + 
                        "memenuhinya. Kepribadian MBTI ESFJ ingin dihargai \n" + 
                        "sebagai pribadi dan atas apa yang telah \n" + 
                        "dikerjakannya. Hangat dan kooperatif, mereka unggul \n" + 
                        "dalam pekerjaan yang melibatkan perawatan dan \n" + 
                        "pelayanan, seperti guru, konselor, manajer sumber \n" + 
                        "daya manusia, perawat, pekerja sosial, event planner, \n" + 
                        "dan pelayanan pelanggan.";
        } else if (result.equals("ESFP")) {
            return "Tipe kepribadian MBTI ini adalah sosok yang ramah, \n" + 
                        "bersahabat, fleksibel, adaptif, spontan, mencintai \n" + 
                        "kehidupannya sendiri dan orang lain. Ia suka belajar \n" + 
                        "dan bekerja bersama orang lain dengan pendekatan \n" + 
                        "yang logis dan realistis. Ramah dan energetik, mereka \n" + 
                        "unggul dalam peran yang membutuhkan interaksi \n" + 
                        "sosial dan hiburan, seperti aktor, musisi, pembawa \n" + 
                        "acara TV, event planner, pemandu wisata, dan guru.";
        } else if (result.equals("ESTJ")) {
            return "ESTJ adalah tipe yang praktis, realistis, berorientasi \n" + 
                        "fakta, dan tegas. Ia tahu bagaimana mengatur \n" + 
                        "pekerjaan secara efisien agar diperoleh hasil terbaik. \n" + 
                        "Standar logika yang dimiliki ESTJ membantunya \n" + 
                        "membuat keputusan dengan cepat, hingga terkadang \n" + 
                        "memaksakan rencananya.Terorganisir dan tegas, ESTJ \n" + 
                        "cocok dalam peran manajemen dan pengawasan, \n" + 
                        "seperti manajer proyek, analis bisnis, inspektur, perwira \n" + 
                        "militer, pengacara, akuntan, dan guru.";
        } else if (result.equals("ESTP")) {
            return "Kepribadian MBTI ESTP adalah sosok yang fleksibel \n" + 
                        "dan toleran, suka menjalin berkomunikasi aktif. Bagi \n" + 
                        "ESTP, teori itu membosankan. Justru mereka belajar \n" + 
                        "dengan baik saat harus melakukan sesuatu secara \n" + 
                        "langsung. Seorang ESTP suka pekerjaan yang penuh \n" + 
                        "aksi dan tantangan, seperti pengusaha, petugas \n" + 
                        "pemadam kebakaran, polisi, atlet, pilot, dan ahli bedah.";
        } else {
            return "";
        }
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result1) {
        this.result = result1;
    }
}