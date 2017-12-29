package object;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private String ID;
    private String name;
    private Date birthdate;
    private Boolean gender;
    private String email;
    private String description;
    private RealmList<Project> projects;

    public static void addUser(final User user) {
        Realm realmDB = Realm.getDefaultInstance();
        realmDB.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public User(String ID, String Name, Date BirthDate, Boolean Gender, String Email, String Description) {
        this.ID = ID;
        this.name = Name;
        this.birthdate = BirthDate;
        this.gender = Gender;
        this.email = Email;
        this.description = Description;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
