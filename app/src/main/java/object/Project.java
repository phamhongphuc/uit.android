package object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import module.callback.VoidCallback;

public class Project extends RealmObject {
    @LinkingObjects("project")
    private final RealmResults<Channel> channels = null;
    @LinkingObjects("project")
    private final RealmResults<Task> tasks = null;
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private RealmList<String> tags;

    private User creator;
    private RealmList<User> members;

    private Date createdate;
    private Date deadline;
    private Date lastupdate;

    public Project() {
    }

    public static Project getProjectById(int projectId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Project project = realm.where(Project.class).equalTo("id", projectId).findFirst();
        realm.commitTransaction();
        return project;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RealmList<String> getTags() {
        return tags;
    }

    public User getCreator() {
        return creator;
    }

    public RealmList<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        if (!members.contains(user)) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            members.add(user);
            realm.commitTransaction();
        }
    }

    public Date getCreatedate() {
        return createdate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public RealmResults<Channel> getChannels() {
        return channels;
    }

    public RealmResults<Task> getTasks() {
        return tasks;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("tasks", tasks);
            obj.put("members", members);
            obj.put("description", description);
            obj.put("creator", creator);
            obj.put("tags", tags);
            obj.put("channels", channels);
            obj.put("createdate", createdate);
            obj.put("deadline", deadline);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public interface Callback {
        void Response(Project project);
    }

    public interface CallbackProjects {
        void Response(ArrayList<Integer> projects, VoidCallback done);
    }
}

