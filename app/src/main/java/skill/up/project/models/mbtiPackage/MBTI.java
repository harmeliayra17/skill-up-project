package skill.up.project.models.mbtiPackage;


public abstract class MBTI {
    private String resultEvsI;
    private String resultSvsN;
    private String resultTvsF;
    private String resultJvsP;

    public abstract void mbtiResult();

    public String getResultEvsI() {
        return resultEvsI;
    }
    public void setResultEvsI(String resultEvsI) {
        this.resultEvsI = resultEvsI;
    }

    public String getResultSvsN() {
        return resultSvsN;
    }

    public void setResultSvsN(String resultSvsN) {
        this.resultSvsN = resultSvsN;
    }

    public String getResultTvsF() {
        return resultTvsF;
    }

    public void setResultTvsF(String resultTvsF) {
        this.resultTvsF = resultTvsF;
    }

    public String getResultJvsP() {
        return resultJvsP;
    }

    public void setResultJvsP(String resultJvsP) {
        this.resultJvsP = resultJvsP;
    }
}