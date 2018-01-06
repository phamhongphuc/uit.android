package components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import uit.group.manager.R;

public class TextInput extends AppCompatEditText {
    public TextInput(Context context) {
        super(context);
        Initialize(context, null);
    }

    public TextInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        Initialize(context, attrs);
    }

    public TextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialize(context, attrs);
    }

    private void Initialize(Context context, AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeView(context);
    }

    private void InitializeView(Context context) {
        setTypeface(
                Typeface.createFromAsset(context.getAssets(), "fonts/segoe.ttf")
        );
        setTextColor(context.getColor(R.color.blue));
        setBackgroundColor(context.getColor(R.color.transparent));
        setForeground(context
                .obtainStyledAttributes(new int[]{R.attr.selectableItemBackground})
                .getDrawable(0)
        );
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float dp = getResources().getDisplayMetrics().density;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height);

        setTextSize(size * 0.35f / dp);
        setPadding(
                (int) (size * 0.5f / dp), 0,
                (int) (size * 0.5f / dp), 0
        );
    }

    @Override
    public void onEditorAction(int actionCode) {
        super.onEditorAction(actionCode);
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            clearFocus();
        }
    }
}

