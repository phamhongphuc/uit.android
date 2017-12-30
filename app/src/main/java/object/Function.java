package object;

import android.support.annotation.NonNull;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import object.User;

/**
 * Created by SofiaJetson on 12/30/2017.
 */

public class Function {

    private static Realm realm = Realm.getDefaultInstance();

    /// 1 lay ID, Ten, email tu socket
    public static User Login(final String ID, final String Name, final String Email) {
        User user = realm.where(User.class).equalTo("id", ID).findFirst();
        if (user == null) {
            realm.beginTransaction();
            user = realm.copyToRealm(new User(ID, Name, Email));
            realm.commitTransaction();
        }
        return user;
    }
    ///3
/*
    public static Project createProject(final User user, final String name, final Date deadline, final String description){
        realm.beginTransaction();

        realm.commitTransaction();
    }
*/
}
