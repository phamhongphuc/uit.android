package components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.facebook.internal.ImageRequest;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import uit.group.manager.R;

import static android.graphics.PorterDuff.Mode.SRC_IN;

public class ImageAvatar extends android.support.v7.widget.AppCompatImageView {
    private final Picasso picasso = Picasso.with(getContext());
    private int outerSize = 0;
    private int innerSize = 0;
    private String userId;
    private Rect target;

    public ImageAvatar(Context context) {
        super(context);
        Initialize(context, null);
    }


    public ImageAvatar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Initialize(context, attrs);
    }

    public ImageAvatar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialize(context, attrs);
    }

    private void Initialize(Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeImage();
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageAvatar);
        userId = typedArray.getString(R.styleable.ImageAvatar__userId);
        innerSize = MeasureSpec.getSize((int) typedArray.getDimension(R.styleable.ImageAvatar__innerSize, 0));
        outerSize = MeasureSpec.getSize((int) typedArray.getDimension(R.styleable.ImageAvatar__outerSize, 0));
        typedArray.recycle();
    }

    private void InitializeImage() {
        if (userId == null) return;
        if (outerSize == 0 || innerSize == 0) return;

        Uri src = ImageRequest.getProfilePictureUri(userId, innerSize, innerSize);
        picasso
                .load(src)
                .resize(innerSize, innerSize)
                .transform(new CircleTransform())
                .into(this);
        picasso.setIndicatorsEnabled(true);
    }

    /**
     * Databinding: set_userId
     *
     * @param userId String
     */
    public void set_userId(String userId) {
        this.userId = userId;
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null || outerSize == 0) return;

        Bitmap source = ((BitmapDrawable) drawable).getBitmap();
        canvas.drawBitmap(source, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(outerSize, outerSize);
        setMeasuredDimension(outerSize, outerSize);
    }

    private class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap input) {
            Bitmap source = Bitmap.createBitmap(input, 0, 0, innerSize, innerSize);
            if (source != input) input.recycle();
            Bitmap output = Bitmap.createBitmap(outerSize, outerSize, input.getConfig());

            int center = outerSize / 2;
            int radius = innerSize / 2;
            int start = center - radius;
            int end = center + radius;
            final Rect rect = new Rect(start, start, end, end);
            final Paint paint = new Paint();
            final Canvas canvas = new Canvas(output);

            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);

            paint.setColor(Color.WHITE);
            canvas.drawCircle(center, center, radius, paint);
            paint.setXfermode(new PorterDuffXfermode(SRC_IN));
            canvas.drawBitmap(source, null, rect, paint);
            source.recycle();
            return output;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}