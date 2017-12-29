package components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;

import uit.group.manager.R;

public class Line extends View {
    private static final Paint paint = new Paint();
    private boolean isFull = false;

    public Line(Context context) {
        super(context, null, R.attr.LineStyle);
        Initialize(context, null);
    }

    public Line(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.LineStyle);
        Initialize(context, attrs);
    }

    public Line(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.attr.LineStyle);
        Initialize(context, attrs);
    }

    public Line(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, R.attr.LineStyle, defStyleRes);
        Initialize(context, attrs);
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Line);
        isFull = typedArray.getBoolean(R.styleable.Button__icon, false);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height);

        if (this.getParent() instanceof LinearLayoutCompat) {
            int dp = (int) getResources().getDisplayMetrics().density;
            switch (((LinearLayoutCompat) this.getParent()).getOrientation()) {
                case LinearLayoutCompat.HORIZONTAL:
                    setMeasuredDimension(dp, size);
                    break;
                case LinearLayoutCompat.VERTICAL:
                    setMeasuredDimension(size, dp);
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        int width = getWidth();
        int height = getHeight();
        float space = isFull ? 0 : 0.15f;
        if (width > height) {
            canvas.drawRect(
                    width * space,
                    0,
                    width * (1 - space),
                    height,
                    paint
            );
        } else if (width < height) {
            canvas.drawRect(
                    0,
                    height * space,
                    width,
                    height * (1 - space),
                    paint
            );
        }
    }
}
