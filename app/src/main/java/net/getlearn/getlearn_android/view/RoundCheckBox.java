package net.getlearn.getlearn_android.view;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/12------更新------
 * 圆形CheckBox
 */

public class RoundCheckBox extends AppCompatCheckBox {

    public  RoundCheckBox(Context context) {
        this(context, null);
    }

    public  RoundCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.radioButtonStyle);
    }

    public  RoundCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
