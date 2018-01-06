package module.socket;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.socket.client.Ack;
import module.callback.Count;
import module.callback.VoidCallback;
import object.Project;

public class _Socket_Project {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void CreateProject(final String userId, final Project.Callback callback) {
        socket.emit("Create:Project(userId)", userId, new Ack() {
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

                    callback.Response(project);
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

    public static void GetProjectById(int projectId,
                                      final Project.Callback responseProject,
                                      @Nullable final Count count) {
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
                    if (count != null) {
                        count.decrease();
                    }
                }
            }
        });
    }
}
