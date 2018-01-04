package components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import uit.group.manager.R;

public class ViewPagerIndex extends View {
    private static final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RATIO = 0.4f;

    private int count = 0;
    private int index = 0;

    // Attr
    private int inactiveColor;
    private int activeColor;
    private int viewPagerId;

    // Measure and Draw
    private int size;
    private int padding;

    public ViewPagerIndex(Context context) {
        super(context, null, R.attr.ViewPagerIndexStyle);
        Initialize(context, null);
    }

    public ViewPagerIndex(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.ViewPagerIndexStyle);
        Initialize(context, attrs);
    }

    public ViewPagerIndex(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.attr.ViewPagerIndexStyle);
        Initialize(context, attrs);
    }

    public ViewPagerIndex(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, R.attr.ViewPagerIndexStyle, defStyleRes);
        Initialize(context, attrs);
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndex);
        activeColor = typedArray.getColor(R.styleable.ViewPagerIndex__activeColor, Color.WHITE);
        inactiveColor = typedArray.getColor(R.styleable.ViewPagerIndex__inactiveColor, Color.BLACK);
        viewPagerId = typedArray.getResourceId(R.styleable.ViewPagerIndex__viewPagerId, -1);
        typedArray.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = getRootView().findViewById(viewPagerId);
        if (view == null || !(view instanceof ViewPager)) return;

        ViewPager viewPager = (ViewPager) view;
        if (viewPager.getAdapter() == null) return;
        count = viewPager.getAdapter().getCount();
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int index) {
                ViewPagerIndex.this.index = index;
                invalidate();
            }
        });
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        size = (int) (height / (1 + 2 * RATIO));
        padding = (int) (size * RATIO);

        setMeasuredDimension(
                size * count + padding * (count + 1),
                size + padding * 2
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < count; i++) {
            paint.setColor(i == index ? activeColor : inactiveColor);
            canvas.drawCircle(
                    size * (i + 0.5f) + padding * (i + 1),
                    size * 0.5f + padding,
                    size * 0.5f, paint
            );
        }
    }
}
