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

public class CircleBackGroundSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#def0ef"));
        c.drawCircle((right - left) / 2, (bottom - top) / 2 + ScaleUtils.dip2px(4f), ScaleUtils.dip2px(18f), paint);
    }
}
