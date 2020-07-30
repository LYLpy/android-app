package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/12------更新------
 */

public class SignInModel {
    private int integral;//积分
    private boolean isSignIn;//是否已签到

    public SignInModel(int integral, boolean isSignIn) {
        this.integral = integral;
        this.isSignIn = isSignIn;
    }

    public SignInModel() {
    }
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }
}
