package net.getlearn.getlearn_android.view.MaterialCalendarView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import net.getlearn.getlearn_android.utils.ScaleUtils;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/10------更新------
 */

public class BackgroundPlanDaySpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas canvas, Paint paint,
                               int left, int right, int top, int baseline, int bottom,
                               CharSequence charSequence,
                               int start, int end, int lineNum) {
        Paint p = new Paint();
        p.setColor(Color.parseColor("#f99f9f9f"));
        canvas.save();
//        canvas.drawColor(Color.parseColor("#ffd6ba"));
//        p.setColor(Color.parseColor("#ffd6ba"));
        canvas.drawCircle((right - left) / 2, bottom + ScaleUtils.dip2px(12), ScaleUtils.dip2px(3), p);
        canvas.restore();
    }
}
