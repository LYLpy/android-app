package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.SubjectIntroduceModel;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ScaleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/2------更新------
 * 课程介绍标签
 */

public class RvAdapterSubjectIntroduce extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private SubjectIntroduceModel mModel;
    private LayoutInflater inflater;
    private List<String> subjectIntroduceList;
    public RvAdapterSubjectIntroduce(Context context, SubjectIntroduceModel model) {
        mContext = context;
        mModel = model;

        this.inflater = LayoutInflater.from(mContext);
        subjectIntroduceList = new ArrayList<>();
        subjectIntroduceList.add("名师辅导");
        subjectIntroduceList.add("免费试听");
        subjectIntroduceList.add("共" + model.getData().getCourse_introduce().getOkResCount() + "课时");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View contentView;
        RecyclerView.ViewHolder holder;
        //头部
        if (i == Constants.ITEM_TYPE_HEADER){
            contentView = inflater.inflate(R.layout.head_subject_introduce,viewGroup,false);
            holder = new RvAdapterSubjectIntroduce.HeaderHolder(contentView);
            //底部
        } else if (i == Constants.ITEM_TYPE_BOTTOM){
            contentView = inflater.inflate(R.layout.bottom_subject_introduce,viewGroup,false);
            holder = new RvAdapterSubjectIntroduce.BottomHolder(contentView);
        }else {
            contentView = inflater.inflate(R.layout.item_subject_list,viewGroup,false);
            holder = new RvAdapterSubjectIntroduce.ContentHolder(contentView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        LogUtil.e("__onBindViewHolder_i",i + "");
        LogUtil.e("__onBindViewHolder_size() + 1", mModel.getData().getFree_list().size() + 1 + "");
        try {
        //头部
        if (i == 0){
            HeaderHolder headerHolder = (HeaderHolder) holder;
            //设置标签RecyclerView数据
            RvAdapterSubjectLabel adapter = new RvAdapterSubjectLabel(mContext,subjectIntroduceList);
//            RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,3);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            headerHolder.rvSubjectLabel.setAdapter(adapter);
            headerHolder.rvSubjectLabel.setLayoutManager(manager);

            headerHolder.tvSubjectName.setText(mModel.getData().getCourse_introduce().getName());
            headerHolder.llVipAdvert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnVipAdvertClickListener != null){
                        mOnVipAdvertClickListener.onVipAdvertClick();
                        LogUtil.e("__mOnVipAdvertClickListener","adapter");
                    }
                }
            });
            Boolean judge = System.currentTimeMillis() > UserManager.getInstance().getVipTime();
            if (UserManager.getInstance().getIsVip()&&judge){
                headerHolder.llVipAdvert.setVisibility(View.GONE);
            }else {
                headerHolder.llVipAdvert.setVisibility(View.VISIBLE);
            }

            //设置老师RecyclerView数据
            RvAdapterTeacherSubjectIntroduce adapterTeacher = new RvAdapterTeacherSubjectIntroduce(mContext,mModel.getData().getTeacher());
            RecyclerView.LayoutManager managerTeacher = new LinearLayoutManager(mContext);
            headerHolder.rvTeacher.setAdapter(adapterTeacher);
            headerHolder.rvTeacher.setLayoutManager(managerTeacher);
            //没有老师数据就把“授课老师”标题隐藏
            if (mModel.getData().getTeacher() == null || mModel.getData().getTeacher().size() == 0){
                headerHolder.tvTeacherTitle.setVisibility(View.GONE);
            }else {
                headerHolder.tvTeacherTitle.setVisibility(View.VISIBLE);
                //设置老师点击事件
                adapterTeacher.setItemClickListener(new RvAdapterTeacherSubjectIntroduce.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (mOnItemTeacherClickListener != null){
                            mOnItemTeacherClickListener.onTeacherItemClick(position);
                        }
                    }
                });
            }
            //底部
        }else if(i == mModel.getData().getFree_list().size() + 1){
            //动态计算图片拉伸之后的高度，然后设置进去
            BottomHolder bottomHolder = (BottomHolder) holder;
            screenWidth = ScaleUtils.getScreenWidth(MainApp.getContext());
            ViewGroup.LayoutParams layoutParams = bottomHolder.iv.getLayoutParams();
            mIvVipPrivilegeHeiht = getImgHeight()[0] * getImgHeight()[1];
            layoutParams.height = (int) mIvVipPrivilegeHeiht;
            layoutParams.width = (int) screenWidth;
            bottomHolder.iv.setLayoutParams(layoutParams);
//            bottomHolder.tvTitle.setText(mModel.getData().getCourse_introduce().getContent());
        //中间部分
        }else {
            //课程名称
            String subjectName = mModel.getData().getCourse_introduce().getName();
            //当前这节课的名称
            String name = mModel.getData().getFree_list().get(i-1).getName();
            ContentHolder contentHolder = (ContentHolder) holder;
            if (name.contains(subjectName)){
                contentHolder.tvTitle.setText(name.replace(subjectName,""));
            }else {
                contentHolder.tvTitle.setText(name);
            }
            contentHolder.tvFree.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            contentHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null){
                        //减去头部之后的position
                        mOnItemClickListener.onItemClick(i-1);
                    }
                }
            });
        }
        }catch (Exception e){
            LogUtil.e("__Exception_getStackTrace",e.getStackTrace().toString());
            LogUtil.e("__Exception_getMessage",e.getMessage());
        }
    }

    /**
     * 计算屏幕跟图片宽度的比例,获得数组，第一个参数是屏幕宽跟图片宽的比例，第二个参数是图片目前的高度
     * @return
     */
    private float screenWidth;//屏幕宽度
    private float mIvVipPrivilegeHeiht;//图片拉伸之后的高度
    private float[] getImgHeight(){
        screenWidth = ScaleUtils.getScreenWidth(MainApp.getContext());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), R.drawable.subject_introduce, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        float i[] = new float[2];
        i[0] = screenWidth/imageWidth;
        i[1] = imageHeight;
        return i;
    }
    @Override
    public int getItemCount() {
        //加上底部跟头部
        return mModel.getData().getFree_list().size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return Constants.ITEM_TYPE_HEADER;
        } else if(position == mModel.getData().getFree_list().size() + 1){
            return  Constants.ITEM_TYPE_BOTTOM;
        }else {
            return Constants.ITEM_TYPE_CONTENT;
        }
    }
    public class HeaderHolder extends RecyclerView.ViewHolder{
        TextView tvSubjectName;
        TextView tvTeacherTitle;
        RecyclerView rvSubjectLabel;
        RecyclerView rvTeacher;
        LinearLayout llVipAdvert;
        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            tvTeacherTitle = itemView.findViewById(R.id.tv_teacher_title);
            tvSubjectName = itemView.findViewById(R.id.tv_subject_name);
            rvSubjectLabel = itemView.findViewById(R.id.rv_subject_label);
            rvTeacher = itemView.findViewById(R.id.rv_teacher);
            llVipAdvert = itemView.findViewById(R.id.ll_vip_advert);
        }
    }

    public class BottomHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        public BottomHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_subject_introduce);
            iv = itemView.findViewById(R.id.iv_subject_introduce);
        }
    }

    public class ContentHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;//课程标题
        TextView tvFree;//免费
        View itemView;//课程标题
        public ContentHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvFree = itemView.findViewById(R.id.tv_free);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnVipAdvertClickListener mOnVipAdvertClickListener;

    public void setOnVipAdvertClickListener(OnVipAdvertClickListener listener){
        mOnVipAdvertClickListener = listener;
    }

    /**
     * VIP广告点击监听
     */
    public interface OnVipAdvertClickListener{
        void onVipAdvertClick();
    }

//    老师点击事件
    private OnTeacheItemrClickListener mOnItemTeacherClickListener;

    public void setOnTeacherItemClickListener(OnTeacheItemrClickListener listener){
        mOnItemTeacherClickListener = listener;
    }
    public interface OnTeacheItemrClickListener{
        void onTeacherItemClick(int position);
    }

    public SubjectIntroduceModel getModel() {
        return mModel;
    }

    public void setModel(SubjectIntroduceModel model) {
        mModel = model;
    }

}
