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
        try {
            socket = IO.socket(Constant.SERVER_URL);
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    int a = 1;
                }
            }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    int a = 1;
                }
            }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    int a = 1;
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    int a = 1;
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
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
