package skill.up.project.models.mbtiPackage;

public class SenseVsIntu extends MBTI {
    private int sensing;
    private int intuition;

    public SenseVsIntu() {
        this.sensing = 0;
        this.intuition = 0;
    }

    public void addSensing() {
        sensing++;
    }

    public void addIntuition() {
        intuition++;
    }

    @Override
    public void mbtiResult() {
        if (sensing >= intuition) {
            setResultSvsN("S");
        } else {
            setResultSvsN("N");
        }
    }

    
}
