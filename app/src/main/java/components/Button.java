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
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Objects;

import uit.group.manager.R;

public class Button extends LinearLayoutCompat {
    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;
    private static final Map<Integer, Integer> ALIGN = ImmutableMap.<Integer, Integer>builder()
            .put(LEFT, Gravity.START)
            .put(CENTER, Gravity.CENTER_HORIZONTAL)
            .put(RIGHT, Gravity.END)
            .build();

    private String text;
    private String icon;
    private boolean active;
    private int backgroundActive;
    private int background;
    private int foreground;

    private AppCompatTextView textView;
    private AppCompatTextView iconView;
    private Drawable selectedItemDrawable;
    private int textAlign;
    private boolean textPaddingLeft;
    private boolean hasRipple;

    public Button(Context context) {
        super(context, null, R.attr.ButtonStyle);
        Initialize(context, null);
    }

    public Button(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.ButtonStyle);
        Initialize(context, attrs);
    }

    public Button(Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, R.attr.ButtonStyle);
        Initialize(context, attrs);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray;

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.Button);
        icon = (String) typedArray.getText(R.styleable.Button__icon);
        text = (String) typedArray.getText(R.styleable.Button__text);
        textAlign = ALIGN.get(typedArray.getInt(R.styleable.Button__textAlign, CENTER)) | Gravity.CENTER_VERTICAL;

        active = typedArray.getBoolean(R.styleable.Button__active, false);
        background = typedArray.getColor(R.styleable.Button__background, Color.TRANSPARENT);
        foreground = typedArray.getColor(R.styleable.Button__foreground, context.getColor(R.color.blue));
        backgroundActive = typedArray.getColor(R.styleable.Button__backgroundActive, Color.parseColor("#333498db"));
        textPaddingLeft = typedArray.getBoolean(R.styleable.Button__textPaddingLeft, true);
        hasRipple = typedArray.getBoolean(R.styleable.Button__hasRipple, true);

        typedArray.recycle();
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeView(context);
        InitializeRipple();
    }

    private void InitializeView(@NonNull Context context) {
        int currentColor = !active ? background : backgroundActive;
        if (icon != null && !Objects.equals(icon, "")) {
            if (iconView == null) {
                iconView = new AppCompatTextView(context);
                addView(iconView);
            }
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
        } else if (iconView != null) {
            removeView(iconView);
        }

        if (text != null && !Objects.equals(text, "")) {
            if (textView == null) {
                textView = new AppCompatTextView(context);
                addView(textView);
            }
            textView.setText(text);
            textView.setTypeface(
                    Typeface.createFromAsset(getContext().getAssets(), "fonts/segoe regular.ttf")
            );
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT
            );
            params.weight = 1;
            textView.setLayoutParams(params);
            textView.setTextColor(foreground);
            textView.setSingleLine(true);
            textView.setBackgroundColor(currentColor);
            textView.setGravity(textAlign);
        } else if (textView != null) {
            removeView(textView);
        }
        requestLayout();
    }

    private void InitializeRipple() {
        setForeground(!active && hasRipple ?
                getContext()
                        .obtainStyledAttributes(new int[]{R.attr.selectableItemBackground})
                        .getDrawable(0) :
                new ColorDrawable(Color.TRANSPARENT)
        );
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
                    (iconView == null && textPaddingLeft)
                            ? (int) (size * 0.5f / dp) : 0,
                    0,
                    (int) (size * 0.5f / dp),
                    0
            );
        }
    }

    public void set_text(String text) {
        this.text = text;
        InitializeView(getContext());
    }

    public void set_icon(String icon) {
        this.icon = icon;
        InitializeView(getContext());
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
        InitializeRipple();
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
}