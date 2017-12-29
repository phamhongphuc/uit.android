package components;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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
    private int backgroundActive;
    private int background;
    private int foreground;

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
        active = typedArray.getBoolean(R.styleable.Button__active, false);
        backgroundActive = typedArray.getColor(R.styleable.Button__activeColor, Color.parseColor("#333498db"));
        background = typedArray.getColor(R.styleable.Button__background, Color.TRANSPARENT);
        foreground = typedArray.getColor(R.styleable.Button__foreground, context.getColor(R.color.blue));
        typedArray.recycle();
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
        AddView(context);
        SetRipple();
    }

    private void AddView(@NonNull Context context) {
//        int currentColor = !active ? background : backgroundActive;
        int currentColor = Color.TRANSPARENT;
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
            iconView.setTextColor(foreground);
            iconView.setBackgroundColor(currentColor);
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
            textView.setTextColor(foreground);
            textView.setBackgroundColor(currentColor);
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
                    0,
                    (int) (size * 0.5f / dp),
                    0
            );
        }
    }

    public void toggleActive() {
        active = !active;
        SetActiveAnimation();
    }

    public void SetActiveAnimation(boolean active) {
        this.active = active;
        SetActiveAnimation();
    }

    public void SetActiveAnimation() {
        SetRipple();
        int colorFrom = active ? background : backgroundActive;
        int colorTo = !active ? background : backgroundActive;

        AnimatorSet set;
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofArgb(iconView, "backgroundColor", colorFrom, colorTo),
                ObjectAnimator.ofArgb(textView, "backgroundColor", colorFrom, colorTo)
        );
        set.setDuration(150);
        if (!set.isRunning()) set.start();

        invalidate();
    }

    private void SetRipple() {
        setForeground(active ?
                new ColorDrawable(Color.TRANSPARENT) :
                getContext()
                        .obtainStyledAttributes(new int[]{R.attr.selectableItemBackground})
                        .getDrawable(0)
        );
    }
}
