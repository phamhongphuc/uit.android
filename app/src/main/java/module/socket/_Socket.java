package module.socket;

import android.databinding.ObservableField;
import android.util.Log;

import java.net.URISyntaxException;

import app.Constant;
import app.Global;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class _Socket {
    public static ObservableField<String> status;
    private static Socket socket;
    private static Global global = Global.getInstance();

    public static void Initialize() {
        getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_CONNECT_ERROR);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        }).on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_CONNECT);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_CONNECT_TIMEOUT);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                global.socketStatus.set(Socket.EVENT_DISCONNECT);
                Log.d("SOCKET EVENT:", global.socketStatus.get());
            }
        });
        _Socket_User.socket_on();

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