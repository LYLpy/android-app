package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 * 今日挑战/本周挑战的bean
 */

public class ChallengeModel {

    private String title;//挑战标题
    private String content;//挑战内容
    private int totalChallenge;//挑战总数
    private int completeChallenge;//完成数量
    private boolean isComplete;//是否已完成
    private int integral;//积分
    public int getTotalChallenge() {
        return totalChallenge;
    }


    public ChallengeModel(String title, int integral,boolean isComplete, int totalChallenge, int completeChallenge) {
        this.title = title;
        this.totalChallenge = totalChallenge;
        this.completeChallenge = completeChallenge;
        this.isComplete = isComplete;
        this.integral = integral;
    }

    public ChallengeModel(String title, int integral, boolean isComplete) {
        this.title = title;
        this.integral = integral;
        this.isComplete = isComplete;
    }
    public ChallengeModel() {
    }
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public void setTotalChallenge(int totalChallenge) {
        this.totalChallenge = totalChallenge;
    }

    public int getCompleteChallenge() {
        return completeChallenge;
    }

    public void setCompleteChallenge(int completeChallenge) {
        this.completeChallenge = completeChallenge;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
