package module.socket;

import android.databinding.ObservableField;
import android.util.Log;

import java.net.URISyntaxException;

import app.Constant;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

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

public class _Socket {
    private static Socket socket;

    public static void Initialize() {
        if (socket != null) return;
        try {
            socket = IO.socket(Constant.SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket_event(EVENT_CONNECT);
        socket_event(EVENT_CONNECT_ERROR);
        socket_event(EVENT_CONNECT_TIMEOUT);
        socket_event(EVENT_CONNECTING);
        socket_event(EVENT_DISCONNECT);
        socket_event(EVENT_ERROR);
        socket_event(EVENT_RECONNECT);
        socket_event(EVENT_RECONNECT_ATTEMPT);
        socket_event(EVENT_RECONNECT_FAILED);
        socket_event(EVENT_RECONNECT_ERROR);
        socket_event(EVENT_RECONNECTING);
        socket.connect();
    }

    private static void socket_event(final String event) {
        socket.on(event, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("SOCKET", event);
                State.INSTANCE.status.set(event);
            }
        });
    }

    public static Socket getSocket() {
        return socket;
    }

    public static class State {
        private static final State INSTANCE = new State();
        public final ObservableField<String> status = new ObservableField<>("");

        public static State getInstance() {
            return INSTANCE;
        }
    }


//    public static Project EditProject(final Project project) {
//        socket.emit("Edit:Project(project)", project, new Ack() {
//            @Override
//            public void CallbackString(Object... args) {
//                Realm realm = Realm.getDefaultInstance();
//                if (args[0] != null) {
//                    Log.d("SOCKET: ERROR", "Lỗi chỉnh sửa: " + args[0]);
//                } else {
//                    JSONObject obj = (JSONObject) args[1];
//                    realm.beginTransaction();
//                    final Project objectFromJson = realm.createOrUpdateObjectFromJson(Project.class, obj);
//                    realm.commitTransaction();
//                }
//            }
//        });
//        return new Project();
//    }
//
//    public static RealmList<Task> GetTasksByProjectId(final String projectId) {
//        socket.emit("Get:Project.Tasks(projectId)", projectId, new Ack() {
//            RealmList<Task> tasks = new RealmList<>();
//
//            @Override
//            public void CallbackString(Object... args) {
//                Realm realm = Realm.getDefaultInstance();
//                if (args[0] != null) {
//                    Log.d("SOCKET: ERROR", "Lỗi trả về: " + args[0]);
//                } else {
//                    JSONArray array = (JSONArray) args[1];
//                    for (int i = 0; i < array.length(); i++) {
//                        try {
//                            int taskId = (int) array.get(i);
//                            tasks.add(realm.where(Task.class).equalTo("id", taskId).findFirst());
//                            /// Sau khi da lay duoc ListTask
//                            /// cần return tasks
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//        return new RealmList<>();
//    }
//
//    public static RealmList<Channel> GetChannelsByProjectId(final String projectId) {
//        socket.emit("Get:Project.Channels(projectId)", projectId, new Ack() {
//            RealmList<Channel> channels = new RealmList<>();
//
//            @Override
//            public void CallbackString(Object... args) {
//                Realm realm = Realm.getDefaultInstance();
//                if (args[0] != null) {
//                    Log.d("SOCKET: ERROR", "Lỗi trả về: " + args[0]);
//                } else {
//                    JSONArray array = (JSONArray) args[1];
//                    for (int i = 0; i < array.length(); i++) {
//                        try {
//                            String channelId = (String) array.get(i);
//                            channels.add(realm.where(Channel.class).equalTo("id", channelId).findFirst());
//                            /// cần return channels
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//        return new RealmList<>();
//    }
//
//    public static RealmList<User> GetMembersByProjectId(final int projectId) {
//        socket.emit("Get:Project.Members(projectId)", new Ack() {
//            RealmList<User> users = new RealmList<>();
//
//            @Override
//            public void CallbackString(Object... args) {
//                Realm realm = Realm.getDefaultInstance();
//                if (args[0] != null) {
//                    Log.d("SOCKET: ERROR", "Lỗi trả về " + args[0]);
//                } else {
//                    JSONArray array = (JSONArray) args[1];
//                    for (int i = 0; i < array.length(); i++) {
//                        try {
//                            String memberId = (String) array.get(i);
//                            users.add(realm.where(User.class).equalTo("id", memberId).findFirst());
//                            ///cần return users
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//        return new RealmList<>();
//    }
}