package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import app.Global;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import object.Project;
import object.User;

public class MainState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();
    public final ObservableField<String> userPicture = new ObservableField<>();
    public final ObservableField<User> user = new ObservableField<>();
    public Global global = Global.getInstance();
    public RealmResults<Project> projects;

    public MainState() {
        Realm realm = Realm.getDefaultInstance();
        user.set(User.getUserById(realm, global.currentUserId.get()));
        if (user.get() == null) user.set(new User());
        userPicture.set(user.get().getPicture());

        updateProjectsOfUser(realm);
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(@NonNull Realm realm) {
                updateProjectsOfUser(realm);
            }
        });
    }

    private void updateProjectsOfUser(Realm realm) {
        projects = user.get().getProjects();
    }
}