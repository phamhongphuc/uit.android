package object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import module._Facebook;

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
    private RealmList<Project> projects;
    private Date lastupdate;

    public User() {
    }

    public static User getUserById(Realm realm, String userId) {
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("id", userId).findFirst();
        realm.commitTransaction();
        return user;
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

    public RealmList<Project> getProjects() {
        return projects;
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
