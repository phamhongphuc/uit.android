package components;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import uit.group.manager.R;

import static io.socket.client.Socket.EVENT_CONNECT;
import static io.socket.client.Socket.EVENT_CONNECTING;
import static io.socket.client.Socket.EVENT_CONNECT_ERROR;
import static io.socket.client.Socket.EVENT_CONNECT_TIMEOUT;
import static io.socket.client.Socket.EVENT_DISCONNECT;
import static io.socket.client.Socket.EVENT_ERROR;
import static io.socket.client.Socket.EVENT_RECONNECT;
import static io.socket.client.Socket.EVENT_RECONNECTING;
import static io.socket.client.Socket.EVENT_RECONNECT_ATTEMPT;
import static io.socket.client.Socket.EVENT_RECONNECT_ERROR;
import static io.socket.client.Socket.EVENT_RECONNECT_FAILED;

public class SocketStatus extends View {
    private static final Map<String, Integer> colors = ImmutableMap.<String, Integer>builder()
            .put(EVENT_CONNECT, 0x2ecc71)
            .put(EVENT_CONNECT_ERROR, 0xc0392b)
            .put(EVENT_CONNECT_TIMEOUT, 0xe74c3c)
            .put(EVENT_CONNECTING, 0x1abc9c)
            .put(EVENT_DISCONNECT, 0xc0392b)
            .put(EVENT_ERROR, 0xc0392b)
            .put(EVENT_RECONNECT, 0x1abc9c)
            .put(EVENT_RECONNECT_ATTEMPT, 0x16a085)
            .put(EVENT_RECONNECT_FAILED, 0xc0392b)
            .put(EVENT_RECONNECT_ERROR, 0xc0392b)
            .put(EVENT_RECONNECTING, 0xabc9c)
            .build();

    private String status;
    private int currentColor;
    private int nextColor;

    public SocketStatus(Context context) {
        super(context);
        Initialize(context, null);
    }

    public SocketStatus(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Initialize(context, attrs);
    }

    public SocketStatus(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialize(context, attrs);
    }

    public SocketStatus(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Initialize(context, attrs);
    }

    private void Initialize(final Context context, AttributeSet attrs) {
        InitializeAttr(context, attrs);
        InitializeView(context);
    }

    private void InitializeAttr(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SocketStatus);
        status = typedArray.getString(R.styleable.SocketStatus__status);
        typedArray.recycle();
    }

    private void InitializeView(Context context) {
        nextColor = colors.get(status);
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
