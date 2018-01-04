package components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class Icon extends AppCompatTextView {

    public Icon(Context context) {
        super(context);
        Initialize(context, null);
    }

    public Icon(Context context, AttributeSet attrs) {
        super(context, attrs);
        Initialize(context, attrs);
    }

    public Icon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialize(context, attrs);
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        setTypeface(
                Typeface.createFromAsset(getContext().getAssets(), "fonts/aicon.ttf")
        );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float dp = getResources().getDisplayMetrics().density;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height);

        setTextSize(size * 0.4f / dp);
        setMeasuredDimension(size, size);
    }
}
