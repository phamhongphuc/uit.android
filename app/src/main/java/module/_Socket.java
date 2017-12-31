package module;

import java.net.URISyntaxException;

import app.Constant;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class _Socket {
    private static Socket socket;
    public static String status;

    public static void Initialize() {
        getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_CONNECT_ERROR;
            }
        }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_CONNECT;
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_CONNECT_TIMEOUT;
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                status = Socket.EVENT_DISCONNECT;
            }
        });
        socket.connect();
    }

    public static Socket getSocket() {
        if (socket == null) {
            try {
                socket = IO.socket(Constant.SERVER_URL);
            } catch (URISyntaxException e) {
                socket = null;
            }
        }
        return socket;
    }

    public static void EmitAccessToken(String accessToken) {
        socket.emit("SetAccessToken", accessToken);
    }
}
