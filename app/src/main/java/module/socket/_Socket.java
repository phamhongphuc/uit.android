package module.socket;

import android.databinding.ObservableField;
import android.util.Log;

import java.net.URISyntaxException;

import app.Constant;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class _Socket {
    private static final _Socket INSTANCE = new _Socket();
    public final ObservableField<String> status = new ObservableField<>();
    private Socket socket;

    private _Socket() {
        InitializeSocket();
        InitializeListener();
    }

    public static Socket getSocket() {
        return getInstance().socket;
    }

    public static _Socket getInstance() {
        return INSTANCE;
    }

    public static void Initialize() {
        _Socket socket = getInstance();
    }

    private void InitializeSocket() {
        try {
            socket = IO.socket(Constant.SERVER_URL);
        } catch (URISyntaxException e) {
            socket = null;
        }
    }

    private void InitializeListener() {
        socket
                .on(Socket.EVENT_CONNECT, emitter(Socket.EVENT_CONNECT))
                .on(Socket.EVENT_CONNECTING, emitter(Socket.EVENT_CONNECTING))
                .on(Socket.EVENT_CONNECT_ERROR, emitter(Socket.EVENT_CONNECT_ERROR))
                .on(Socket.EVENT_CONNECT_TIMEOUT, emitter(Socket.EVENT_CONNECT_TIMEOUT))
                .on(Socket.EVENT_RECONNECT, emitter(Socket.EVENT_RECONNECT))
                .on(Socket.EVENT_RECONNECT_ERROR, emitter(Socket.EVENT_RECONNECT_ERROR))
                .on(Socket.EVENT_RECONNECT_FAILED, emitter(Socket.EVENT_RECONNECT_FAILED))
                .on(Socket.EVENT_RECONNECT_ATTEMPT, emitter(Socket.EVENT_RECONNECT_ATTEMPT))
                .on(Socket.EVENT_RECONNECTING, emitter(Socket.EVENT_RECONNECTING));
        socket.connect();
        _Socket_User.socket_on();
    }

    private Emitter.Listener emitter(final String event) {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("SOCKET", event);
//                global.socketStatus.set(event);
                status.set(event);
            }
        };
    }

//    public static Project EditProject(final Project project) {
//        socket.emit("Edit:Project(project)", project, new Ack() {
//            @Override
//            public void call(Object... args) {
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
//            public void call(Object... args) {
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
//            public void call(Object... args) {
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
//            public void call(Object... args) {
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