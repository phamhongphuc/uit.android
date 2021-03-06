package object;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.ArrayList;
import java.util.Date;

import io.realm.ProjectRealmProxy;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import module.callback.VoidCallback;

@RealmClass
@Parcel(implementations = {ProjectRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Project.class})
public class Project extends RealmObject {
    @LinkingObjects("project")
    private final RealmResults<Channel> channels = null;

    @LinkingObjects("project")
    private final RealmResults<Task> tasks = null;

    @PrimaryKey
    private int id;
    private String name;
    private String description;

    private User creator;
    private RealmList<User> members = new RealmList<>();

    private Date createdate;
    private Date deadline;
    private Date lastupdate;

    public Project() {
    }

    public static Project getProjectById(int projectId) {
        Realm realm = Realm.getDefaultInstance();
        Project project;

        realm.beginTransaction();
        project = realm.where(Project.class).equalTo("id", projectId).findFirst();
        realm.commitTransaction();

        return project;
    }

    public JSONObject getJson(boolean isMember, boolean isTask, boolean isChannel) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("description", description);
            obj.put("creator", creator.getJson());
            obj.put("createdate", createdate);
            obj.put("deadline", deadline);

            obj.put("members", User.getJSONArray(members));
            obj.put("tasks", Task.getJSONArray(tasks));
            obj.put("channels", Channel.getJSONArray(channels));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public RealmResults<Channel> getChannels() {
        return channels;
    }

    public RealmResults<Task> getTasks() {
        return tasks;
    }

    public int getId() {
        return id;
    }

    // ----- GETTER AND SETTER ----- //

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public RealmList<User> getMembers() {
        return members;
    }

    @ParcelPropertyConverter(User.RealmListUserParcelConverter.class)
    public void setMembers(RealmList<User> members) {
        this.members = members;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public interface Callback {
        void Response(Project project);
    }

    public interface CallbackWithUser {
        void Response(Project project, User user);
    }

    public interface CallbackProjects {
        void Response(ArrayList<Integer> projects, VoidCallback done);
    }
}
