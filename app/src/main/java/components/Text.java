package components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import uit.group.manager.R;

public class Text extends AppCompatTextView {
    public Text(Context context) {
        super(context, null, R.attr.TextStyle);
        Initialize(context, null);
    }

    public Text(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.TextStyle);
        Initialize(context, attrs);
    }

    public Text(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.attr.TextStyle);
        Initialize(context, attrs);
    }

    private void Initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        setTypeface(
                Typeface.createFromAsset(getContext().getAssets(), "fonts/segoe.ttf")
        );
        setTextColor(context.getColor(R.color.blue));
    }
}
