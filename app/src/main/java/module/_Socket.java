package module;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import app.Constant;
import app.Global;
import io.realm.Realm;
import io.realm.RealmList;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import object.Channel;
import object.Project;
import object.Task;
import object.User;

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

        //        socket.on("UserData", new Emitter.Listener() {
        //            @Override
        //            public void call(Object... args) {
        //                JSONObject user = (JSONObject) args[0];
        //                String id = null;
        //                String email = null;
        //                String name = null;
        //                try {
        //                    id = (String) user.get("id");
        //                    name = (String) user.get("name");
        //                    email = (String) user.get("email");
        //                } catch (JSONException e) {
        //                    e.printStackTrace();
        //                }
        //                if (id != null) {
        //                    User.SetCurrentUser(id, name, email);
        //                }
        //            }
        //        });

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

    /**
     * Sau khi gọi cái này, máy chủ sẽ emit ngược về "UserData"
     */
    public static void GetUserByAccessToken() {
        if (AccessToken.getCurrentAccessToken() != null) {
            String accessToken = AccessToken.getCurrentAccessToken().getToken();
            String id = AccessToken.getCurrentAccessToken().getUserId();
            socket.emit("Get:User(accessToken, id)", accessToken, id, new Ack() {
                @SuppressLint("LongLogTag")
                @Override
                public void call(Object... args) {
                    Realm realm = Realm.getDefaultInstance();
                    if (args[0] != null) {
                        Log.d("SOCKET: ERROR", "Lỗi trả về" + args[0]);
                    } else {
                        JSONObject obj = (JSONObject) args[1];
                        realm.beginTransaction();
                        final User user = realm.createOrUpdateObjectFromJson(User.class, obj);
                        realm.commitTransaction();

                        Global.getInstance().currentUserId.set(user.getId());
                    }
                }
            });
        }
    }

    public static RealmList<Project> GetProjectsByUserId(String userId) {
        socket.emit("Get:User.Projects(userId)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi lấy projects từ id" + args[0]);
                } else {
                    JSONArray array = (JSONArray) args[1];
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            int projectId = (int) array.get(i);
                            GetProjectById(projectId);
                            // chua tra ve duoc projects
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        return new RealmList<>();
    }

    public static void GetProjectById(int projectId) {
        final Project objectFromJson;
        socket.emit("Get:Project(projectId)", projectId, new Ack() {
            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi trả về" + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];
                    realm.beginTransaction();
                    final Project objectFromJson = realm.createOrUpdateObjectFromJson(Project.class, obj);
                    /// Sau khi
                    realm.commitTransaction();
                }
            }
        });
    }

    public static Project EditProject(final Project project) {
        socket.emit("Edit:Project(project)", project, new Ack() {
            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi chỉnh sửa: " + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];
                    realm.beginTransaction();
                    final Project objectFromJson = realm.createOrUpdateObjectFromJson(Project.class, obj);
                    /// Sau khi chinh sua project
                    realm.commitTransaction();
                }
            }
        });
        return new Project();
    }

    public static Project CreateProject(final String userId) {
        socket.emit("Create:Project(userId)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi thêm mới: " + args[0]);
                } else {
                    realm.beginTransaction();
                    final Project newProject = realm
                            .createOrUpdateObjectFromJson(Project
                                    .class, (JSONObject) args[1]);
                    /// sau khi da update xong, tra ve newProject
                    realm.commitTransaction();
                }
            }
        });
        return new Project();
    }

    public static RealmList<Task> GetTasksByProjectId(final String projectId) {
        socket.emit("Get:Project.Tasks(projectId)", projectId, new Ack() {
            RealmList<Task> tasks = new RealmList<>();

            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi trả về: " + args[0]);
                } else {
                    JSONArray array = (JSONArray) args[1];
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            int taskId = (int) array.get(i);
                            tasks.add(realm.where(Task.class).equalTo("id", taskId).findFirst());
                            /// Sau khi da lay duoc ListTask
                            /// cần return tasks
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return new RealmList<>();
    }

    public static RealmList<Channel> GetChannelsByProjectId(final String projectId) {
        socket.emit("Get:Project.Channels(projectId)", projectId, new Ack() {
            RealmList<Channel> channels = new RealmList<>();

            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi trả về: " + args[0]);
                } else {
                    JSONArray array = (JSONArray) args[1];
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            String channelId = (String) array.get(i);
                            channels.add(realm.where(Channel.class).equalTo("id", channelId).findFirst());
                            /// cần return channels
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return new RealmList<>();
    }

    public static RealmList<User> GetMembersByProjectId(final int projectId) {
        socket.emit("Get:Project.Members(projectId)", new Ack() {
            RealmList<User> users = new RealmList<>();

            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi trả về " + args[0]);
                } else {
                    JSONArray array = (JSONArray) args[1];
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            String memberId = (String) array.get(i);
                            users.add(realm.where(User.class).equalTo("id", memberId).findFirst());
                            ///cần return users
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return new RealmList<>();
    }
}