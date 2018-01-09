package object;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.Date;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.TaskRealmProxy;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
@Parcel(implementations = {TaskRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Task.class})
public class Task extends RealmObject {
    public static final int ONGOING = 0;
    public static final int ONHOLD = 1;
    public static final int COMPLETE = 2;

    @PrimaryKey
    private int id;
    private String name;
    private String description;

    private Project project;
    private User assigned;
    private RealmList<User> subscribers;

    private int status;
    private Date createdate;
    private Date deadline;
    private Date lastupdate;

    public Task() {
    }

    public static Task getTaskById(int taskId) {
        Realm realm = Realm.getDefaultInstance();
        Task task;

        realm.beginTransaction();
        task = realm.where(Task.class).equalTo("id", taskId).findFirst();
        realm.commitTransaction();

        return task;
    }

    public static JSONArray getJSONArray(@Nullable RealmList<Task> tasks) {
        JSONArray jsonArray = new JSONArray();
        if (tasks != null) for (Task task : tasks) jsonArray.put(task.getJson());
        return jsonArray;
    }

    public static JSONArray getJSONArray(@Nullable RealmResults<Task> tasks) {
        JSONArray jsonArray = new JSONArray();
        if (tasks != null) for (Task task : tasks) jsonArray.put(task.getJson());
        return jsonArray;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("description", description);

            obj.put("status", status);
            obj.put("deadline", deadline);
            obj.put("createdate", createdate);

            if (assigned != null) obj.put("assigned", assigned.getJson());
            if (subscribers != null) obj.put("subscribers", User.getJSONArray(subscribers));
            if (project != null) obj.put("project", project.getJson(false, false, false));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public int getId() {
        return id;
    }

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public RealmList<User> getSubscribers() {
        return subscribers;
    }

    @ParcelPropertyConverter(User.RealmListUserParcelConverter.class)
    public void setSubscribers(RealmList<User> subscribers) {
        this.subscribers = subscribers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        void Response(Task task);
    }
}