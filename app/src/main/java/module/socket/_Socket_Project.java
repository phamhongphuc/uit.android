package module.socket;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.socket.client.Ack;
import module.callback.VoidCallback;
import object.Project;
import object.User;

public class _Socket_Project {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void CreateProject(final User user, final Project.CallbackWithUser callback) {
        socket.emit("Create:Project(userId)", user.getId(), new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi thêm mới: " + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Project project = realm.createOrUpdateObjectFromJson(Project.class, obj);
                    realm.commitTransaction();

                    callback.Response(project, user);
                }
            }
        });
    }

    public static void GetProjectIdsByUserId(String userId, final Project.CallbackProjects callback, @Nullable final VoidCallback done) {
        socket.emit("Get:Projects(userId)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", "Lỗi lấy projects từ id" + args[0]);
                } else {
                    JSONArray array = (JSONArray) args[1];
                    ArrayList<Integer> projectIds = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            projectIds.add(array.getInt(i));
                        } catch (JSONException ignored) {
                            ignored.printStackTrace();
                        }
                    }
                    callback.Response(projectIds, done);
                }
            }
        });
    }

    public static void GetProjectById(int projectId, final Project.Callback responseProject) {
        socket.emit("Get:Project(projectId)", projectId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("SOCKET ERROR", "Lỗi trả về: " + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    final Project project = realm.createOrUpdateObjectFromJson(Project.class, obj);
                    realm.commitTransaction();

                    responseProject.Response(project);
                }
            }
        });
    }

    public static void GetProjectMemberIdsByProjectId(int projectId, final User.CallbackUsers callback, @Nullable final VoidCallback done) {
        socket.emit("Get:Project.members(projectId)", projectId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", args[0].toString());
                } else {
                    JSONArray array = (JSONArray) args[1];
                    ArrayList<String> userIds = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            userIds.add(array.getString(i));
                        } catch (JSONException ignored) {
                            ignored.printStackTrace();
                        }
                    }
                    callback.Response(userIds, done);
                }
            }
        });
    }

    public static void EditProject(final Project project, final String userId, final @Nullable VoidCallback voidCallback) {
        JSONObject projectJson = project.getJson(true, true, true);
        socket.emit("Edit:Project(project, userId)", projectJson, userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", args[0].toString());
                } else {
                    if (voidCallback != null) {
                        voidCallback.Response();
                    }
                }
            }
        });
    }

    public static void AddMember(final int projectId, final String email, final Project.Callback callback) {
        socket.emit("Add:Project.Member(projectId, email)", projectId, email, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", args[0].toString());
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    final Project project = realm.createOrUpdateObjectFromJson(Project.class, obj);
                    realm.commitTransaction();

                    callback.Response(project);
                }
            }
        });
    }

    public static void DeleteProject(final int projectId, final String userId, final VoidCallback callback) {
        socket.emit("Delete:Project(projectId, userId)", projectId, userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", args[0].toString());
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Project.getProjectById(projectId).deleteFromRealm();
                    realm.commitTransaction();

                    callback.Response();
                }
            }
        });
    }
}
