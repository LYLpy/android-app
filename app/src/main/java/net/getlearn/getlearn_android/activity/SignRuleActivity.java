package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.anim.HeightAnim;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/16------更新------
 * 签到规则
 */

public class SignRuleActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.iv_arrow_sign_rule)
    ImageView mIvArrowSignRule;
    @BindView(R.id.rl_sign_rule)
    RelativeLayout mRlSignRule;
    @BindView(R.id.tv_content_sign_rule)
    TextView mTvContentSignRule;
    @BindView(R.id.iv_arrow_rights_and_interests)
    ImageView mIvArrowRightsAndInterests;
    @BindView(R.id.rl_rights_and_interests)
    RelativeLayout mRlRightsAndInterests;
    @BindView(R.id.iv_content_rights_and_interests)
    ImageView mIvContentRightsAndInterests;
    @BindView(R.id.iv_arrow_integral_source)
    ImageView mIvArrowIntegralSrc;
    @BindView(R.id.rl_integral_source)
    RelativeLayout mRlIntegralSrc;
    @BindView(R.id.iv_content_integral_source)
    ImageView mIvContentIntegralSrc;
    @BindView(R.id.iv_arrow_integral_consume)
    ImageView mIvArrowIntegralConsume;
    @BindView(R.id.rl_integral_consume)
    RelativeLayout mRlIntegralConsume;
    @BindView(R.id.tv_content_integral_consume)
    TextView mTvContentIntegralConsume;

    private boolean isAnimRunningSignRule;//签到规则动画是否播放中
    private boolean isAnimRunningRightAndInterests;//等级权益动画是否播放中
    private boolean isAnimRunningIntegralSrc;//积分来源动画是否播放中
    private boolean isAnimRunningIntegralConsume;//积分消费动画是否播放中

    private boolean isShowSignRule = false;//签到规则是否展示出来
    private boolean isShowRightAndInterests = false;//等级权益是否展示出来
    private boolean isShowIntegralSrc = false;//积分来源是否展示出来
    private boolean isShowIntegralConsume = false;//积分消费是否展示出来

    private int mContentHeighSignRule;//签到规则最初高度
    private int mContentHeighRightAndInterests;//等级权益最初高度
    private int mContentHeighIntegralSrc;//积分来源最初高度
    private int mContentHeighIntegralConsume;//积分消费最初高度
    //哪个条目
    private final int ITEM_SIGNRULE = 1;//签到规则
    private final int ITEM_RIGHTANDINTERESTE = 2;//等级权益
    private final int ITEM_INTEGRALSRC = 3;//积分来源
    private final int ITEM_INTEGRALCONSUME = 4;//积分消费

    @Override
    protected void initData() {
        mTvContentIntegralConsume.setText(Html.fromHtml(getResources().getString(R.string.integral_consume)));

        ViewGroup.LayoutParams params1 = mTvContentSignRule.getLayoutParams();
        mContentHeighSignRule = params1.height;
        params1.height = 0;
        mTvContentSignRule.setLayoutParams(params1);


        ViewGroup.LayoutParams params2 = mIvContentRightsAndInterests.getLayoutParams();
        mContentHeighRightAndInterests = params2.height;
        params2.height = 0;
        mIvContentRightsAndInterests.setLayoutParams(params2);


        ViewGroup.LayoutParams params3 = mIvContentIntegralSrc.getLayoutParams();
        mContentHeighIntegralSrc = params3.height;
        params3.height = 0;
        mIvContentIntegralSrc.setLayoutParams(params3);


        ViewGroup.LayoutParams params4 = mTvContentIntegralConsume.getLayoutParams();
        mContentHeighIntegralConsume = params4.height;
        params4.height = 0;
        mTvContentIntegralConsume.setLayoutParams(params4);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sign_rule;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mRlSignRule.setOnClickListener(this);
        mRlRightsAndInterests.setOnClickListener(this);
        mRlIntegralSrc.setOnClickListener(this);
        mRlIntegralConsume.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.rl_sign_rule:
                if (isAnimRunningSignRule)return;
                startAnim(ITEM_SIGNRULE);
                break;
            case R.id.rl_rights_and_interests:
                if (isAnimRunningRightAndInterests)return;
                startAnim(ITEM_RIGHTANDINTERESTE);
                break;
            case R.id.rl_integral_source:
                if (isAnimRunningIntegralSrc)return;
                startAnim(ITEM_INTEGRALSRC);
                break;
            case R.id.rl_integral_consume:
                if (isAnimRunningIntegralConsume)return;
                startAnim(ITEM_INTEGRALCONSUME);
                 break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }

    private void startAnim(int item){
        //它只负责让数值进行变化，并不会有什么动画效果
        HeightAnim anim;
        switch (item){
            case ITEM_SIGNRULE:
                if(isShowSignRule){
                    //说明要关闭
                    anim = new HeightAnim(mTvContentSignRule, mContentHeighSignRule, 0);
                }else {
                    //说明要展开
                    anim = new HeightAnim(mTvContentSignRule, 0, mContentHeighSignRule);
                }
                anim.start(500);
                //取反
                isShowSignRule = !isShowSignRule;
                //箭头旋转
                ViewCompat.animate(mIvArrowSignRule)
                        .setListener(new ViewPropertyAnimatorListenerAdapter(){
                            @Override
                            public void onAnimationEnd(View view) {
                                isAnimRunningSignRule = false;
                            }
                            @Override
                            public void onAnimationStart(View view) {
                                isAnimRunningSignRule = true;
                            }
                        })
                        .rotationBy(180).setDuration(500).start();
                break;
                case ITEM_RIGHTANDINTERESTE:
                    if(isShowRightAndInterests){
                        //说明要关闭
                        anim = new HeightAnim(mIvContentRightsAndInterests, mContentHeighRightAndInterests, 0);
                    }else {
                        //说明要展开
                        anim = new HeightAnim(mIvContentRightsAndInterests, 0, mContentHeighRightAndInterests);
                    }
                    anim.start(500);
                    //取反
                    isShowRightAndInterests = !isShowRightAndInterests;
                    //箭头旋转
                    ViewCompat.animate(mIvArrowRightsAndInterests)
                            .setListener(new ViewPropertyAnimatorListenerAdapter(){
                                @Override
                                public void onAnimationEnd(View view) {
                                    isAnimRunningRightAndInterests = false;
                                }
                                @Override
                                public void onAnimationStart(View view) {
                                    isAnimRunningRightAndInterests = true;
                                }
                            })
                            .rotationBy(180).setDuration(500).start();
                    break;
            case ITEM_INTEGRALSRC:
                if(isShowIntegralSrc){
                    //说明要关闭
                    anim = new HeightAnim(mIvContentIntegralSrc, mContentHeighIntegralSrc, 0);
                }else {
                    //说明要展开
                    anim = new HeightAnim(mIvContentIntegralSrc, 0, mContentHeighIntegralSrc);
                }
                anim.start(500);
                //取反
                isShowIntegralSrc = !isShowIntegralSrc;
                //箭头旋转
                ViewCompat.animate(mIvArrowIntegralSrc)
                        .setListener(new ViewPropertyAnimatorListenerAdapter(){
                            @Override
                            public void onAnimationEnd(View view) {
                                isAnimRunningIntegralSrc = false;
                            }
                            @Override
                            public void onAnimationStart(View view) {
                                isAnimRunningIntegralSrc = true;
                            }
                        })
                        .rotationBy(180).setDuration(500).start();
                break;
            case ITEM_INTEGRALCONSUME:
                if(isShowIntegralConsume){
                    //说明要关闭
                    anim = new HeightAnim(mTvContentIntegralConsume, mContentHeighIntegralConsume, 0);
                }else {
                    //说明要展开
                    anim = new HeightAnim(mTvContentIntegralConsume, 0, mContentHeighIntegralConsume);
                }
                anim.start(500);
                //取反
                isShowIntegralConsume = !isShowIntegralConsume;
                //箭头旋转
                ViewCompat.animate(mIvArrowIntegralConsume)
                        .setListener(new ViewPropertyAnimatorListenerAdapter(){
                            @Override
                            public void onAnimationEnd(View view) {
                                isAnimRunningIntegralConsume = false;
                            }
                            @Override
                            public void onAnimationStart(View view) {
                                isAnimRunningIntegralConsume = true;
                            }
                        })
                        .rotationBy(180).setDuration(500).start();
                break;
        }



    }

//    class AnimListener extends ViewPropertyAnimatorListenerAdapter{
//        @Override
//        public void onAnimationEnd(View view) {
//            isRunAnim = false;
//        }
//        @Override
//        public void onAnimationStart(View view) {
//            isRunAnim = true;
//        }
//    }

}
