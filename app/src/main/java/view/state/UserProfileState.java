package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import app.Global;
import io.realm.Realm;
import io.realm.RealmList;
import module._Socket;
import object.Task;
import object.User;

public class UserProfileState extends BaseObservable {
    public final ObservableField<User> user = new ObservableField<>();
    public final ObservableField<RealmList<Task>> tasks = new ObservableField<>();
    private final io.socket.client.Socket socket = _Socket.getSocket();

    public UserProfileState() {
        user.set(Global.user.get());
        tasks.set(Global.project.get().getTasks());
//        Realm realm = Realm.getDefaultInstance();
//        User user = realm.where(User.class).equalTo("id")
//        //        socket.
    }
}
