package components;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import uit.group.manager.R;

public class Button extends LinearLayoutCompat {
    private String text;
    private String icon;
    private boolean active = false;
    private TextView textView;
    private TextView iconView;
    private Drawable selectedItemDrawable;

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
//        background = (Color) typedArray.getColor(R.styleable.)
        typedArray.recycle();
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
        setBackground(context
                .obtainStyledAttributes(new int[]{R.attr.selectableItemBackground})
                .getDrawable(0)
        );

        if (icon != null) {
            iconView = new TextView(context);
            iconView.setText(icon);
            iconView.setTypeface(
                    Typeface.createFromAsset(getContext().getAssets(), "fonts/aicon.ttf")
            );
            iconView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT
            ));
            iconView.setGravity(Gravity.CENTER);
            addView(iconView);
        }
        if (text != null) {
            textView = new TextView(context);
            textView.setText(text);
            textView.setTypeface(
                    Typeface.createFromAsset(getContext().getAssets(), "fonts/segoe.ttf")
            );
            textView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT
            ));
            textView.setGravity(Gravity.CENTER);
            addView(textView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float dp = getResources().getDisplayMetrics().density;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height);
        setMinimumHeight(size);

        if (iconView != null) {
            iconView.setTextSize(size * 0.4f / dp);
            iconView.setWidth(size);
        }
        if (textView != null) {
            textView.setTextSize(size * 0.35f / dp);
            textView.setPadding(
                    iconView == null ? (int) (size * 0.5f / dp) : 0,
                    (int) (size * 0.1f / dp),
                    (int) (size * 0.5f / dp),
                    0
            );
        }
    }

    public void setActive() {
        setActive(!this.active);
    }

    public void setActive(Boolean active) {
        if (this.active == active) return;
        this.active = active;
        ObjectAnimator animator = ObjectAnimator.ofInt(
                this,
                "backgroundColor",
                this.active ?
                        Color.TRANSPARENT :
                        Color.parseColor("#66000000"),
                this.active ?
                        Color.parseColor("#66000000") :
                        Color.TRANSPARENT
        );
        animator.setDuration(150);
        animator.setEvaluator(new ArgbEvaluator());
        if (!animator.isRunning()) animator.start();
        invalidate();
    }
}
