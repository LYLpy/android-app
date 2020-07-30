package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterSearchResult;
import net.getlearn.getlearn_android.model.bean.SearchResultModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/20------更新------
 *
 */

public class SearchResultActivity extends BaseActivity implements RvAdapterSearchResult.OnItemClickListener {

    @BindView(R.id.toolbar)
    RelativeLayout mToolbarIndex;
    @BindView(R.id.tv_amount_search_result)
    TextView mTvSearchAmount;
    @BindView(R.id.rv_search_result)
    RecyclerView mRv;
    @BindView(R.id.rl_search_result)
    RelativeLayout mRlSearchResult;
    @BindView(R.id.iv_search_no_result)
    ImageView mIvSearchNoResult;
    @BindView(R.id.tv_not_coupon)
    TextView mTvSearchNoResult;
    @BindView(R.id.rl_no_result)
    RelativeLayout mRlNoResult;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    @BindView(R.id.btn_research)
    Button mBtnResearch;

    private String mContent;
    private SearchResultModel.DatabeanX mData;
    private RvAdapterSearchResult mRvAdapter;
    private int page = 1;
    @Override
    protected void initData() {
        page = 1;
        mData = null;
        Intent intent = getIntent();
        mContent = intent.getStringExtra("content");
        mHttpHelper.search(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),mContent,page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mBtnResearch.setOnClickListener(this);
        initSpringView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.btn_research:
                Intent intent = new Intent(this,SearchActivity.class);
                finish();
                startActivity(intent);

//                showProgressDialog("正在搜索");
//                mHttpHelper.search(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),mContent,page);
                break;
        }
    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_SEARCH:
                SearchResultModel searchResultModel = (SearchResultModel) msg.obj;
                setData(searchResultModel);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
//        dismissProgressDialog();
        switch (reqType){
            case Constants.REQUEST_SEARCH:
                if (mData.getData() != null && mData.getData().size() != 0 ){
//                LogUtil.e("__mData.getData","nodatav_gone");
                    mRlNoResult.setVisibility(View.GONE);
                }else {
                    mRlNoResult.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(this));
        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                page = 1;
                mData = null;
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mRvAdapter!=null){
                            mRvAdapter.removeData();
                        }
                        mHttpHelper.search(SearchResultActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),mContent,page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.search(SearchResultActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),mContent,page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    /**
     * 设置数据
     * @param searchResultModel
     */
    public void setData(SearchResultModel searchResultModel){
//        dismissProgressDialog();
        page++;
        String source = "共找到" + searchResultModel.getData().getTotal() + "门 “" + RichtextUtil.getBlue(mContent) + "” 相关的课程";
        Spanned spanned =  Html.fromHtml(source);
        mTvSearchAmount.setText(spanned);
        if (mData == null){
            mData = searchResultModel.getData();
            mRvAdapter = new RvAdapterSearchResult(this,mData);
            mRvAdapter.setOnItemClickListener(this);
            mRv.setLayoutManager(new LinearLayoutManager(this));
//            mRvAdapter.setHasStableIds(true);
            mRv.setAdapter(mRvAdapter);
//            mRv.setItemAnimator(null);
//            mRvAdapter.setHasStableIds(true);
//            LogUtil.e("__mData.getData",String.valueOf(mData.getData()));
//            LogUtil.e("__mData.getData",String.valueOf(mData.getData()));
//            LogUtil.e("__mData.getData.size",String.valueOf(mData.getData().size()));
        }else {
            int positionOri =  mData.getData().size() - 1;
            List<SearchResultModel.DatabeanX.Databean> data = mData.getData();
            List<SearchResultModel.DatabeanX.Databean> newData = searchResultModel.getData().getData();
            data.addAll(newData);
//            mLvAdapter.notifyDataSetChanged();
//                mLv.smoothScrollToPosition(currentPosition);
//            mRvAdapter.notifyDataSetChanged();
//            if (mData.getData().size() > positionOri){
//                mRv.smoothScrollToPosition(mData.getData().size() -1);
//            }

//            mRvAdapter.notifyItemRangeChanged(0,positionOri);

//            mRv.setItemAnimator(null);//避免刷新数据之后失去焦点无法点击
            mRvAdapter.notifyDataSetChanged();
//            mRvAdapter.notifyItemRangeChanged(positionOri,mRvAdapter.getItemCount());
//            mRvAdapter.setHasStableIds(true);
//            mRv.setItemAnimator(null);//避免刷新数据之后失去焦点无法点击
//            mRvAdapter.setHasStableIds(true);//避免刷新数据之后失去焦点无法点击
//            mRv.smoothScrollToPosition(mData.getData().size() - 14);
//            mRvAdapter.setOnItemClickListener(this);
//            mRv.setItemAnimator(null);
        }
        if (mData.getData() == null || mData.getData().size() == 0 ){
//                LogUtil.e("__mData.getData","nodatav_gone");
            mRlNoResult.setVisibility(View.VISIBLE);
        }else {
            mRlNoResult.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,VideoPlayActivity.class);
        intent.putExtra("course_id",mData.getData().get(position).getId());
        LogUtil.e("__onItemClick_position",position + "");
        LogUtil.e("__onItemClick_course_id",mData.getData().get(position).getId() + "");
        startActivity(intent);
    }
}
