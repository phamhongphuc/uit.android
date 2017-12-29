package module;

import java.net.URISyntaxException;

import app.App;
import app.Constant;
import app.State;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class _Socket {
    private static Socket socket;
    private static State state = App.state;

    public static void Initialize() {
        try {
            socket = IO.socket(Constant.SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (socket != null) {
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    state.status.set("Timeout, không thể kết nối");
                }
            }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    socket.emit("foo", "Tín hiệu này được gửi đi từ điện thoại");
                    state.status.set("Đã gửi đi một tín hiệu");
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    state.status.set("Đã ngắt kết nối");
                }
            }).on("eventName", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    state.status.set("Trả về." + args[0]);
                }
            });
            socket.connect();
        }
    }

    public static Socket Socket() {
        if (socket == null) Initialize();
        return socket;
    }

    public static void EmitAccessToken(String accessToken) {
        socket.emit("SetAccessToken", accessToken);
    }
}
