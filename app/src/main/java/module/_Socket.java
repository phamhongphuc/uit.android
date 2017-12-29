package module;

import java.net.URISyntaxException;

import app.Constant;
import io.socket.client.IO;
import io.socket.client.Socket;

public class _Socket {
    private static Socket socket;
    public static String status;

    public static void Initialize() {
        try {
            if (socket == null) socket = IO.socket(Constant.SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (socket != null) socket.connect();
    }

    public static Socket Socket() {
        if (socket == null) Initialize();
        return socket;
    }

    public static void EmitAccessToken(String accessToken) {
        socket.emit("SetAccessToken", accessToken);
    }
}
