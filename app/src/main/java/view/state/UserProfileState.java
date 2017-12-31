package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import app.Global;
import module._Socket;
import object.User;

/**
 * Created by SofiaJetson on 12/31/2017.
 */

public class UserProfileState extends BaseObservable {
    public final ObservableField<User> user = new ObservableField<>();
    private final io.socket.client.Socket socket = _Socket.getSocket();

    public UserProfileState() {
        user.set(Global.user.get());
//        Realm realm = Realm.getDefaultInstance();
//        User user = realm.where(User.class).equalTo("id")
//        //        socket.
    }
}
