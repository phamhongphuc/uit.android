package object;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.UserRealmProxy;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import module.callback.VoidCallback;

@Parcel(implementations = {UserRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {User.class})
public class User extends RealmObject {
    public static final boolean FEMALE = false;
    public static final boolean MALE = false;
    @LinkingObjects("members")
    private final RealmResults<Project> projects = null;
    @LinkingObjects("creator")
    private final RealmResults<Project> projectsOwn = null;
    @LinkingObjects("subscribers")
    private final RealmResults<Task> tasks = null;
    @LinkingObjects("assigned")
    private final RealmResults<Task> tasksOwn = null;
    @LinkingObjects("members")
    private final RealmResults<Channel> channels = null;
    @LinkingObjects("assigned")
    private final RealmResults<Channel> channelsOwn = null;
    @PrimaryKey
    private String id;
    private String name;
    private Date birthdate;
    private Boolean gender;
    private String email;
    private String description;
    private Date lastupdate;

    public User() {
    }

    public static User getUserById(String userId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("id", userId).findFirst();
        realm.commitTransaction();
        realm.close();
        return user;
    }

    public RealmResults<Project> getProjects() {
        return projects;
    }

    public RealmResults<Project> getProjectsOwn() {
        return projectsOwn;
    }

    public RealmResults<Task> getTasks() {
        return tasks;
    }

    public RealmResults<Task> getTasksOwn() {
        return tasksOwn;
    }

    public RealmResults<Channel> getChannels() {
        return channels;
    }

    public RealmResults<Channel> getChannelsOwn() {
        return channelsOwn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
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

    public interface Callback {
        void Response(User user);
    }

    public interface CallbackUserId {
        void Response(String userId);
    }

    public interface CallbackUsers {
        void Response(ArrayList<String> userIds, VoidCallback done);
    }
}