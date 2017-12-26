package components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import uit.group.manager.R;

public class Line extends View {
    protected int size;

    public Line(Context context) {
        super(context, null, R.attr.LineStyle);
        init(context, null);
    }

    public Line(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.attr.LineStyle);
        init(context, attrs);
    }

    public Line(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.attr.LineStyle);
        init(context, attrs);
    }

    public Line(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, R.attr.LineStyle, defStyleRes);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        // do something here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View parent = (View) this.getParent();
        if (parent instanceof LinearLayout || parent instanceof LinearLayoutCompat) {
            size = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
            float dp = getResources().getDisplayMetrics().density;
            switch (((LinearLayoutCompat) this.getParent()).getOrientation()) {
                case LinearLayout.HORIZONTAL:
                    setMeasuredDimension((int) dp, size);
                    break;
                case LinearLayout.VERTICAL:
                    setMeasuredDimension(size, (int) dp);
                    break;
                default:
                    setMeasuredDimension(size, size);
            }
        } else {
            setMeasuredDimension(size, size);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        @SuppressLint("DrawAllocation")
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        if (getWidth() > getHeight()) {
            canvas.drawRect(getWidth() * 0.15f, 0, getWidth() * 0.85f, getHeight(), paint);
        } else if (getWidth() < getHeight()) {
            canvas.drawRect(0, getHeight() * 0.15f, getWidth(), getHeight() * 0.85f, paint);
        }
    }
}
