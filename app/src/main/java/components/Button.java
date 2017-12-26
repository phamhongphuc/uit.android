package components;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import uit.group.manager.R;

public class Button extends android.support.v7.widget.AppCompatButton {
    private boolean mActive = false;

    public Button(@NonNull Context context) {
        super(context, null, R.attr.ButtonStyle);
        init(context, null, null);
    }

    public Button(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.ButtonStyle);
        init(context, attrs, null);
    }

    public Button(@NonNull Context context, String icon) {
        super(context, null, R.attr.ButtonStyle);
        init(context, null, icon);
    }

    public Button(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, R.attr.ButtonStyle);
        init(context, attrs, null);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, @Nullable String icon) {
        this.setTypeface(
                Typeface.createFromAsset(getContext().getAssets(), "fonts/aicon.ttf")
        );
        this.setText(
                attrs != null ?
                        context.obtainStyledAttributes(attrs, R.styleable.Button).getText(R.styleable.Button__icon) :
                        icon
        );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = Math.min(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec)
        );

        // setTextSize(26);
        setTextSize(size * 0.4f / getResources().getDisplayMetrics().density);
        setMeasuredDimension(size, size);
    }

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
