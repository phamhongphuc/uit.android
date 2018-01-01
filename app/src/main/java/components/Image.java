package components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

import uit.group.manager.R;

public class Image extends android.support.v7.widget.AppCompatImageView {
    private String url;
    private int size;

    public Image(Context context) {
        super(context);
        Initialize(context, null);
    }


    public Image(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Initialize(context, attrs);
    }

    public Image(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialize(context, attrs);
    }

    private void Initialize(Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeView(context);
    }

    private void InitializeView(Context context) {
        Picasso.with(getContext())
                .load(url)
                .into(this);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Image);
        url = typedArray.getString(R.styleable.Image__url);
        typedArray.recycle();
    }

    public void set_url(String url) {
        this.url = url;
        InitializeView(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float dp = getResources().getDisplayMetrics().density;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        size = Math.min(width, height);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null || size == 0) return;

        Bitmap source = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = source.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap roundBitmap = getCroppedBitmap(bitmap, size);
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }

    public static Bitmap getCroppedBitmap(Bitmap input, int radius) {
        Bitmap bitmap;

        if (input.getWidth() != radius || input.getHeight() != radius) {
            float smallest = Math.min(input.getWidth(), input.getHeight());
            float factor = smallest / radius;
            bitmap = Bitmap.createScaledBitmap(input,
                    (int) (input.getWidth() / factor),
                    (int) (input.getHeight() / factor), false);
        } else {
            bitmap = input;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final String color = "#BAB399";
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
