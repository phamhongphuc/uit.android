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
}
