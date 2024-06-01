package skill.up.project.models.mbtiPackage;

public class ThinkVsFeel extends MBTI {
    private int thinking;
    private int feeling;

    public ThinkVsFeel() {
        this.thinking = 0;
        this.feeling = 0;
    }

    public void addThinking() {
        thinking++;
    }

    public void addFeeling() {
        feeling++;
    }

    @Override
    public void mbtiResult() {
        if (thinking >= feeling) {
            setResultTvsF("T");
        } else {
            setResultTvsF("F");
        }
    }
}
