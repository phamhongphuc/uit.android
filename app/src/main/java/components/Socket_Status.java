package components;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Objects;

import uit.group.manager.R;

import static io.socket.client.Socket.EVENT_CONNECT;

public class Socket_Status extends View {
    private static final int RED = 0xffe74c3c;
    private static final int GREEN = 0xff2ecc71;
    private String status;
    private int currentColor;
    private int nextColor;

    public Socket_Status(Context context) {
        super(context);
        Initialize(context, null);
    }

    public Socket_Status(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Initialize(context, attrs);
    }

    public Socket_Status(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialize(context, attrs);
    }

    public Socket_Status(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Initialize(context, attrs);
    }

    private void Initialize(final Context context, AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeView(context);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Socket_Status);
        status = typedArray.getString(R.styleable.Socket_Status__socketStatus);
        typedArray.recycle();
    }

    private void InitializeView(Context context) {
        nextColor = Objects.equals(status, EVENT_CONNECT) ? GREEN : RED;
        if (currentColor == nextColor) return;

        AnimatorSet set;
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofArgb(
                        this,
                        "backgroundColor",
                        currentColor,
                        nextColor
                )
        );
        set.setDuration(70);
        if (!set.isRunning()) set.start();
        currentColor = nextColor;
        invalidate();
    }

    public void set_socketStatus(String status) {
        this.status = status;
        InitializeView(getContext());
    }
}
