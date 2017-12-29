package components;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import uit.group.manager.R;

public class Button extends LinearLayoutCompat {
    private String text;
    private String icon;
    private boolean mActive = false;

    public Button(@NonNull Context context) {
        super(context, null, R.attr.ButtonStyle);
        Initialize(context, null);
    }

    public Button(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.ButtonStyle);
        Initialize(context, attrs);
    }

    public Button(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, R.attr.ButtonStyle);
        Initialize(context, attrs);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Button);
        icon = (String) typedArray.getText(R.styleable.Button__icon);
        text = (String) typedArray.getText(R.styleable.Button__text);
        typedArray.recycle();
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);

        TextView iconView = new TextView(context);
//        iconView.setTypeface();
        iconView.setText(icon);
        addView(iconView);

        TextView textView = new TextView(context);
//        iconView.setTypeface();
        textView.setText(text);
        addView(textView);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        int size = Math.min(
////                MeasureSpec.getSize(widthMeasureSpec),
////                MeasureSpec.getSize(heightMeasureSpec)
////        );
//
//        // setTextSize(26);
////        setTextSize(size * 0.4f / getResources().getDisplayMetrics().density);
////        setMeasuredDimension(size, size);
//    }

    public void setActive() {
        setActive(!this.mActive);
    }

    public void setActive(Boolean active) {
        if (this.mActive == active) return;
        this.mActive = active;
        ObjectAnimator animator = ObjectAnimator.ofInt(
                this,
                "backgroundColor",
                this.mActive ?
                        Color.TRANSPARENT :
                        Color.parseColor("#66000000"),
                this.mActive ?
                        Color.parseColor("#66000000") :
                        Color.TRANSPARENT
        );
        animator.setDuration(150);
        animator.setEvaluator(new ArgbEvaluator());
        if (!animator.isRunning()) animator.start();
        invalidate();
    }
}
