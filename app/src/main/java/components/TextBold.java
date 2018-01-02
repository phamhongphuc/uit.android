package components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import uit.group.manager.R;

public class TextBold extends AppCompatTextView {
    public TextBold(Context context) {
        super(context, null, R.attr.TextStyle);
        Initialize(context, null);
    }

    public TextBold(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.TextStyle);
        Initialize(context, attrs);
    }

    public TextBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.attr.TextStyle);
        Initialize(context, attrs);
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        setTypeface(
                Typeface.createFromAsset(getContext().getAssets(), "fonts/segoe bold.ttf")
        );
    }
}
