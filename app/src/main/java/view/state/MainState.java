package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import app.Global;
import io.realm.Realm;
import io.realm.RealmResults;
import object.Project;
import object.User;

public class MainState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();
    public final ObservableField<String> userPicture = new ObservableField<>();
    public final ObservableField<User> user = new ObservableField<>();
    public Global global = Global.getInstance();
    public ObservableField<RealmResults<Project>> projects;

    public MainState() {
        Realm realm = Realm.getDefaultInstance();
        user.set(User.getUserById(realm, global.currentUserId.get()));
        if (user.get() == null) user.set(new User());

        UpdateListProject();

        userPicture.set(user.get().getPicture());
    }

    public void UpdateListProject() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        projects.set(realm.where(Project.class).findAll());
        realm.commitTransaction();
    }
}