package net.getlearn.getlearn_android.view.MaterialCalendarView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/10------更新------
 */

public class BackgroundSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas canvas, Paint paint,
                               int left, int right, int top, int baseline, int bottom,
                               CharSequence charSequence,
                               int start, int end, int lineNum) {
        canvas.save();
        canvas.drawColor(Color.parseColor("#ffd6ba"));
        canvas.restore();
    }
}
