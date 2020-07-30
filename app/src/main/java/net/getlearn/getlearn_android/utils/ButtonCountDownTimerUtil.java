package net.getlearn.getlearn_android.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/5------更新------
 * 弱引用防止内存泄漏的CountDownTimer
 */

public class ButtonCountDownTimerUtil extends CountDownTimer {

    WeakReference<TextView> mTextView; //显示倒计时的文字  用弱引用 防止内存泄漏
    private String tickStr = "秒后可重新获取";
    private String finishStr = "重新获取验证码";
    public void setTickStr(String tickStr) {
        this.tickStr = tickStr;
    }

    public void setFinishStr(String finishStr) {
        this.finishStr = finishStr;
    }

    /**
     * @param textView
     * @param millisInFuture 总秒数，单位毫秒
     * @param countDownInterval 倒数间隔，单位毫秒
     */
    public ButtonCountDownTimerUtil(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = new WeakReference(textView);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //用弱引用 先判空 避免崩溃
        if (mTextView.get() == null) {
            cancle();
            return;
        }
        mTextView.get().setClickable(false); //设置不可点击
        mTextView.get().setText("重新获取(" + millisUntilFinished / 1000 + "s)"); //设置倒计时时间
//        mTextView.setBackgroundResource(R.drawable.validate_code_press_bg); //设置按钮为灰色，这时是不能点击的
//        SpannableString spannableString = new SpannableString(mTextView.getText().toString()); //获取按钮上的文字
//        @SuppressLint("ResourceAsColor") ForegroundColorSpan span = new ForegroundColorSpan(R.color.text_gray);
//        /**
//         * public void setSpan(Object what, int start, int end, int flags) {
//         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
//         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
//         */
//        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
//        mTextView.get().setText(spannableString);
        mTextView.get().setText(mTextView.get().getText().toString());
        if (mCallBack != null){
            mCallBack.onTick();
        }
    }
    @Override
    public void onFinish() {
        //用软引用 先判空 避免崩溃
        if (mTextView.get() == null){
            cancle();
            return;
        }
        mTextView.get().setText("重新获取");
        mTextView.get().setClickable(true);//重新获得点击
        if (mCallBack != null){
            mCallBack.onFinish();
        }
//      mTextView.setBackgroundResource(R.drawable.validate_code_normal_bg); //还原背景色
    }
    public void cancle() {
        if (this != null) {
            this.cancel();
        }
    }

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }
    private CallBack mCallBack;
    public interface CallBack{
        void onTick();
        void onFinish();
    }
}

