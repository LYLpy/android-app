package net.getlearn.getlearn_android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;

import net.getlearn.getlearn_android.R;
/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/3------更新------
 * 可折叠textview
 */

public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {
    private static final int DEFAULT_TRIM_LENGTH = 50;
    private static final String ELLIPSIS = ".....";
 
    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;
 
    public ExpandableTextView(Context context) {
        this(context, null);
    }
 
    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();
 
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                setText();
                requestFocusFromTouch();
            }
        });
    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }
 
    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }
 
    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }
 
    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {
            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {
            return originalText;
        }
    }
 
    public CharSequence getOriginalText() {
        return originalText;
    }
 
    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }
 
    public int getTrimLength() {
        return trimLength;
    }
}