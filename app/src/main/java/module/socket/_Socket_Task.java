package module.socket;

import android.util.Log;

import com.facebook.AccessToken;

import org.json.JSONObject;

import io.realm.Realm;
import io.socket.client.Ack;
import module.callback.VoidCallback;
import object.Task;

public class _Socket_Task {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void CreateTask(final int projectId, final Task.Callback callback) {
        String userId = AccessToken.getCurrentAccessToken().getUserId();
        socket.emit("Create:Task(projectId, userId)", projectId, userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi thêm mới: " + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Task task = realm.createOrUpdateObjectFromJson(Task.class, obj);
                    realm.commitTransaction();
                    realm.close();

                    callback.Response(task);
                }
            }
        });
    }

    public static void EditTask(final Task task, final Task.Callback callback) {
        String userId = AccessToken.getCurrentAccessToken().getUserId();
        socket.emit("Edit:Task(task, userId)", task.getJson(), userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.d("SOCKET: ERROR", "Lỗi chỉnh sửa: " + args[0]);
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Task task = realm.createOrUpdateObjectFromJson(Task.class, obj);
                    realm.commitTransaction();
                    realm.close();

                    callback.Response(task);
                }
            }
        });
    }

    public static void DeleteTask(final int taskId, final String userId, final VoidCallback callback) {
        socket.emit("Delete:Task(taskId, userId)", taskId, userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", args[0].toString());
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Task.getTaskById(taskId).deleteFromRealm();
                    realm.commitTransaction();

                    callback.Response();
                }
            }
        });
    }

    public static void AddMember(final int taskId, final String userId, final Task.Callback callback) {
        socket.emit("Join:Task(taskId, userId)", taskId, userId, new Ack() {
            @Override
            public void call(Object... args) {
                if (args[0] != null) {
                    Log.e("SOCKET: ERROR", args[0].toString());
                } else {
                    JSONObject obj = (JSONObject) args[1];

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    final Task task = realm.createOrUpdateObjectFromJson(Task.class, obj);
                    realm.commitTransaction();

                    callback.Response(task);
                }
            }
        });
    }
}
