package skill.up.project.models.mbtiPackage;

public class ExtraVsIntro extends MBTI {
    private int extraversion;
    private int introversion;

    public ExtraVsIntro() {
        this.extraversion = 0;
        this.introversion = 0;
    }

    public void addExtraversion() {
        extraversion++;
    }

    public void addIntroversion() {
        introversion++;
    }

    @Override
    public void mbtiResult() {
        if (extraversion >= introversion) {
            setResultEvsI("E");
        } else {
            setResultEvsI("I");
        }
    }
}
