package module.socket;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.socket.client.Ack;
import module.callback._Callback;
import object.Project;

public class _Socket_Project {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    //    public static void CreateProject(final String userId) {
//        socket.emit("Create:Project(userId)", userId, new Ack() {
//            @Override
//            public void call(Object... args) {
//                Realm realm = Realm.getDefaultInstance();
//                if (args[0] != null) {
//                    Log.d("SOCKET: ERROR", "Lỗi thêm mới: " + args[0]);
//                } else {
//                    realm.beginTransaction();
//                    Project project = realm.createOrUpdateObjectFromJson(Project.class, (JSONObject) args[1]);
//                    User user = User.getUserById(userId);
//                    project.addMember(user);
//                    realm.commitTransaction();
//                }
//            }
//        });
//    }
//
    public static void GetProjectIdsByUserId(String userId, final _Callback callback) {
        socket.emit("Get:Projects(userId)", userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi lấy projects từ id" + args[0]);
                } else {
                    JSONArray array = (JSONArray) args[1];
                    ArrayList<Integer> projects = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        int projectId;
                        try {
                            projectId = (int) array.get(i);
                            projects.add(projectId);
                        } catch (JSONException e) {/* */}
                    }
                    callback.Response(projects);
                }
            }
        });
    }

    public static void GetProjectById(int projectId) {
        socket.emit("Get:Project(projectId)", projectId, new Ack() {
            @Override
            public void call(Object... args) {
                Realm realm = Realm.getDefaultInstance();
                if (args[0] != null) {
                    Log.d("SOCKET ERROR", "Lỗi trả về: " + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];
                    realm.beginTransaction();
                    String creatorId;
                    JSONArray membersId;
                    final Project project = realm.createOrUpdateObjectFromJson(Project.class, obj);
                    try {
                        creatorId = obj.getString("creatorId");
                        membersId = obj.getJSONArray("membersId");
//                        project.setCreator(realm, creatorId);
                        // TODO: setMember
//                        project.addMembers(membersId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    realm.commitTransaction();
                }
            }
        });
    }
}
