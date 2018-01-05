package object;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import module.socket._Socket_User;

public class Project extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Task> tasks;
    private RealmList<User> members;
    private String description;
    private User creator;
    private RealmList<String> tags;
    private RealmList<Channel> channels;
    private Date createdate;
    private Date deadline;
    private Date lastupdate;

    public Project() {
    }

    public static Project getProjectById(int projectId) {
        Realm realm = Realm.getDefaultInstance();
        boolean in = realm.isInTransaction();
        if (!in) realm.beginTransaction();
        Project project = realm.where(Project.class).equalTo("id", projectId).findFirst();
        if (!in) realm.commitTransaction();
        return project;
    }

    ///Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RealmList<Task> getTasks() {
        return tasks;
    }

    public int getTasksCount(int status) {
        return getTasks(status).size();
    }

    public RealmResults<Task> getTasks(int status) {
        return tasks.where().equalTo("State", status).findAll();
    }

    public RealmList<User> getMembers() {
        return members;
    }

    public String getDescription() {
        return description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public RealmList<String> getTags() {
        return tags;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public RealmList<Channel> getChannels() {
        return channels;
    }

    public Date getDeadline() {
        return deadline;
    }

    ///Add tung phan tu (channel, task, member<>(), tag)
    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addMember(RealmList<User> members) {
        this.members.addAll(members);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
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

    public void setCreator(Realm realm, String creatorId) {
        User user = User.getUserById(creatorId);
        if (user != null) creator = user;
        else {
            Log.e("ERROR", "Ngoại lệ");
        }
    }

    public void addMember(User user) {
        Realm realm = Realm.getDefaultInstance();
        boolean in = realm.isInTransaction();
        if (!in) realm.beginTransaction();
        if (user != null && user.getId() != null) {
            RealmResults<User> users = members.where().contains("id", user.getId()).findAll();
            if (users.size() == 0) members.add(user);
        }
        if (!in) realm.commitTransaction();
    }

    public void addMembers(JSONArray membersId) {
        Realm realm = Realm.getDefaultInstance();
        for (int i = 0; i < membersId.length(); i++) {
            try {
                String userId = membersId.getString(i);
                User user = User.getUserById(userId);
                if (user != null) addMember(user);
                else {
                    final ObservableField<User> userField = new ObservableField<>();
                    userField.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                        @Override
                        public void onPropertyChanged(Observable sender, int propertyId) {
                            Object value = ((ObservableField) sender).get();
                        }
                    });
                    _Socket_User.GetUserById(userId, userField);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

