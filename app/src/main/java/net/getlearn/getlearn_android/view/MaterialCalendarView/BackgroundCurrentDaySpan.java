package net.getlearn.getlearn_android.view.MaterialCalendarView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.style.LineBackgroundSpan;

import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ScaleUtils;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/10------更新------
 */

public class BackgroundCurrentDaySpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas canvas, Paint paint,
                               int left, int right, int top, int baseline, int bottom,
                               CharSequence charSequence,
                               int start, int end, int lineNum) {
        paint.setColor(0);
        Paint p = new Paint();
        p.setColor(Color.parseColor("#3495F9"));

        canvas.save();
        Rect rect  = new Rect();
        String tempStr = "今";
        Paint p2 = new Paint();
        p2.setColor(Color.parseColor("#ffffff"));
        p2.setTextSize(ScaleUtils.dip2px(18));
        p2.getTextBounds(tempStr,0,1,rect);
        p.setAntiAlias(true);
        p2.setAntiAlias(true);
//        canvas.drawColor(Color.parseColor("#ffd6ba"));
//        p.setColor(Color.parseColor("#ffd6ba"));
//        canvas.drawCircle((right - left) / 2, bottom + ScaleUtils.dip2px(12), ScaleUtils.dip2px(3), p);
//        canvas.drawCircle((right - left) / 2, (bottom - top) / 2, ScaleUtils.dip2px(18f), p);
        canvas.drawCircle((right - left) / 2, (bottom - top) / 2 + 1, (right - left) / 2 - 1 , p);
        LogUtil.e("__",rect.width() + "");
        LogUtil.e("__",rect.height() + "");
        canvas.drawText(tempStr,(right - left) / 2 - (rect.width()/2), (bottom - top) / 2 + (rect.height()/2), p2);
        canvas.restore();
    }
}
