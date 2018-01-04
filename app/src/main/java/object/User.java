package object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import module.facebook._Facebook;

public class User extends RealmObject {
    public static final boolean FEMALE = false;
    public static final boolean MALE = false;

    @PrimaryKey
    private String id;
    private String name;
    private Date birthdate;
    private Boolean gender;
    private String email;
    private String description;
    private Date lastupdate;
//  private final RealmResults<Project> projects;

    public User() {
    }


    public static User getUserById_client(String userId) {
        Realm realm = Realm.getDefaultInstance();
        boolean in = realm.isInTransaction();
        if (!in) realm.beginTransaction();
        User user = realm.where(User.class).equalTo("id", userId).findFirst();
        if (!in) realm.commitTransaction();
        return user;
    }

    public static void getUserById_socket(String userId) {

    }

    public String getId() {
        return id;
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

    public String getPicture() {
        return _Facebook.GetPicture(id);
    }

    public String getDescription() {
        return description;
    }

    public RealmResults<Project> getProjects() {
        return Realm.getDefaultInstance()
                .where(Project.class)
                .contains("members.id", id)
                .findAll();
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("email", email);
            obj.put("birthdate", birthdate);
            obj.put("gender", gender);
            obj.put("description", description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
