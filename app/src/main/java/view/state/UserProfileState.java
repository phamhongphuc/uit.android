package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import io.realm.RealmList;
import module.object.Task;
import module.object.User;
import module.socket._Socket;

public class UserProfileState extends BaseObservable {
    public final ObservableField<User> user = new ObservableField<>();
    public final ObservableField<RealmList<Task>> tasks = new ObservableField<>();
    private final io.socket.client.Socket socket = _Socket.getSocket();

    public UserProfileState() {
//        user.set(OldGlobal.user.get());
//        tasks.set(OldGlobal.projects.get().getTasks());
//        Realm realm = Realm.getDefaultInstance();
//        User user = realm.where(User.class).equalTo("id")
//        //        socket.
    }
}
