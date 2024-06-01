package skill.up.project.models.mbtiPackage;

public class JudgeVsPercieve extends MBTI {
    private int judging;
    private int perceiving;

    public JudgeVsPercieve() {
        this.judging = 0;
        this.perceiving = 0;
    }

    public void addJudging() {
        judging++;
    }

    public void addPerceiving() {
        perceiving++;
    }

    @Override
    public void mbtiResult() {
        if (judging >= perceiving) {
            setResultJvsP("J");
        } else {
            setResultJvsP("P");
        }
    }
}
