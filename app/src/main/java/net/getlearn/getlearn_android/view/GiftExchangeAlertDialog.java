package net.getlearn.getlearn_android.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/6------更新------
 * 兑换礼品AlertDialog
 */

public class GiftExchangeAlertDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private ImageView ivIcon;
    private ImageView ivDismiss;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;


    public GiftExchangeAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public GiftExchangeAlertDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_gift_exchange_dialog, null);

        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        ivDismiss = (ImageView) view.findViewById(R.id.iv_dismiss);
        ivDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setGone();
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }
    /**
     * 恢复初始
     * @return
     */
    public GiftExchangeAlertDialog setGone() {
        if (lLayout_bg != null) {
            txt_title.setVisibility(View.GONE);
            txt_msg.setVisibility(View.GONE);
            btn_neg.setVisibility(View.GONE);
            btn_pos.setVisibility(View.GONE);
            img_line.setVisibility(View.GONE);
        }
        showTitle = false;
        showMsg = false;
        showPosBtn = false;
        showNegBtn = false;
        return this;
    }

    /**
     * 设置title
     * @param title
     * @return
     */
    public GiftExchangeAlertDialog setTitle(String title) {
        showTitle = true;
        if (TextUtils.isEmpty(title)) {
            txt_title.setText("提示");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    /**
     * 设置图片url
     * @param imgUrl
     * @return
     */
    public GiftExchangeAlertDialog setImgUrl(String imgUrl) {
        //Glide加载圆角图片
//        RoundedCorners roundedCorners= new RoundedCorners(40);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(context).load(imgUrl).apply(options).into(ivIcon);
        return this;
    }

    /**
     * 设置title
     * @param title
     * @return
     */
    public GiftExchangeAlertDialog setTitle(String title,int color) {
        showTitle = true;
        if (TextUtils.isEmpty(title)) {
            txt_title.setText("提示");
        } else {
            txt_title.setText(title);
        }
        txt_title.setTextColor(ContextCompat.getColor(context, color));
        return this;
    }


    /**
     * 设置Message
     * @param msg
     * @return
     */
    public GiftExchangeAlertDialog setMsg(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
            txt_msg.setText("");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     * @param cancel
     * @return
     */
    public GiftExchangeAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 右侧按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public GiftExchangeAlertDialog setPositiveButton(String text,
                                            final View.OnClickListener listener) {
        return setPositiveButton(text, -1, listener);
    }

    public GiftExchangeAlertDialog setPositiveButton(String text, int color,
                                            final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("");
        } else {
            btn_pos.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_blue;
        }
        btn_pos.setTextColor(ContextCompat.getColor(context, color));
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 左侧按钮
     *
     * @param text
     * @param listener
     * @return
     */

    public GiftExchangeAlertDialog setNegativeButton(String text,
                                            final View.OnClickListener listener) {

        return setNegativeButton(text, -1, listener);
    }

    public GiftExchangeAlertDialog setNegativeButton(String text, int color,
                                            final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("");
        } else {
            btn_neg.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_blue;
        }
        btn_neg.setTextColor(ContextCompat.getColor(context, color));

        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 设置显示
     */
    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alert_dialog_left_selector_5);
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alert_dialog_right_selector_5);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alert_dialog_left_selector_5);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alert_dialog_selector_blue5_5);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alert_dialog_selector_5);
        }
    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog != null) {
            if (dialog.isShowing())
                return true;
            else
                return false;
        }
        return false;
    }

    public void dismiss() {
        if (dialog!=null){
            dialog.dismiss();
        }

    }
    public GiftExchangeAlertDialog setMsgGravity(int gravity){
        txt_msg.setGravity(gravity);
        return this;
    }
    public GiftExchangeAlertDialog setTitleStyle(Typeface typeface){
        txt_title.setTypeface(typeface);
        return this;
    }
}
