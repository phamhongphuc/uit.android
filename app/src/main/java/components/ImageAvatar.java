package components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
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

import uit.group.manager.R;

public class ImageAvatar extends android.support.v7.widget.AppCompatImageView {
    private static final Paint paint = new Paint();
    private Picasso picasso = Picasso.with(getContext());
    private ObservableInt outerSize = new ObservableInt(0);
    private String userId;
    private Rect target;
    private int innerSize;

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

    public static Bitmap getCroppedBitmap(Bitmap input, int size) {
        Bitmap bitmap;

        if (input.getWidth() != size || input.getHeight() != size) {
            float smallest = Math.min(input.getWidth(), input.getHeight());
            float factor = smallest / size;
            bitmap = Bitmap.createScaledBitmap(input,
                    (int) (input.getWidth() / factor),
                    (int) (input.getHeight() / factor), false);
        } else {
            bitmap = input;
        }

        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(
                size / 2,
                size / 2,
                size / 2,
                paint
        );
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, size, size), paint);

        return output;
    }

    private void Initialize(Context context, @Nullable AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeView(context);
        InitializeListener();
    }

    private void InitializeListener() {
        outerSize.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                InitializeView(getContext());
                int padding = (outerSize.get() - innerSize) / 2;
                target = new Rect(
                        padding,
                        padding,
                        outerSize.get() - padding,
                        outerSize.get() - padding
                );
            }
        });
    }

    private void InitializeView(Context context) {
        if (userId == null) return;
        if (outerSize.get() == 0) return;
        Uri src = ImageRequest.getProfilePictureUri(userId, outerSize.get(), outerSize.get());
        picasso.load(src).into(this);
        picasso.setIndicatorsEnabled(true);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageAvatar);
        userId = typedArray.getString(R.styleable.ImageAvatar__userId);
        innerSize = MeasureSpec.getSize((int) typedArray.getDimension(R.styleable.ImageAvatar__size, 0));
        typedArray.recycle();
    }

    /**
     * Databinding: set_userId
     *
     * @param userId String
     */
    public void set_userId(String userId) {
        this.userId = userId;
        InitializeView(getContext());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        outerSize.set(Math.min(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec)
        ));
        setMeasuredDimension(outerSize.get(), outerSize.get());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null || outerSize.get() == 0) return;

        Bitmap source = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = source.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap roundBitmap = getCroppedBitmap(bitmap, outerSize.get());

        canvas.drawBitmap(roundBitmap, null, target, null);
    }
}
