package object;

import java.lang.reflect.Member;
import java.util.Date;

import bolts.Task;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Project extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Task> tasks;
    private RealmList<User> members;
    private String description;
    private User assigned;
    private RealmList<String> tags;
    private RealmList<Channel> channels;
    private Date createdate;
    private Date deadline;

    public Project(int id, String name, String description, User assigned, Date deadline, RealmList<User> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.createdate = new Date();
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RealmList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(RealmList<Task> tasks) {
        this.tasks = tasks;
    }

    public RealmList<User> getMembers() {
        return members;
    }

    public void setMembers(RealmList<User> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public RealmList<String> getTags() {
        return tags;
    }

    public void setTags(RealmList<String> tags) {
        this.tags = tags;
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

    public RealmList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(RealmList<Channel> channels) {
        this.channels = channels;
    }
}

