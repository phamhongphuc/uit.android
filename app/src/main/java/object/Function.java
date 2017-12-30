package object;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

public class Function {

    /// 1 lay ID, Ten, email tu socket
    public static User Login(final String ID, final String Name, final String Email) {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).equalTo("id", ID).findFirst();
        if (user == null) {
            realm.beginTransaction();
            user = realm.copyToRealm(new User(ID, Name, Email));
            realm.commitTransaction();
        }
        return user;
    }

    ///3
    public static Project createProject(final int id,final User assigned,
                                        final String name, final Date deadline,
                                        final String description,final RealmList<User> members) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Project newProject = new Project(id, name, description, assigned, deadline, members);
        realm.copyToRealm(newProject);
        realm.commitTransaction();
        return newProject;
    }

}
