package object;

import java.lang.reflect.Member;
import java.util.Date;

import bolts.Task;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Project extends RealmObject{
    @PrimaryKey
    private String ID;
    private String Name;
    private RealmList<Task> Tasks;
    private RealmList<User> Members;
    private String Description;
    private Member Assigned;
    private RealmList<String> Tags;
    private RealmList<Channel> Channels;
    private Date CreateDate;
    private Date Deadline;

    public Project(String id, String name, String description, Date deadline) {
        ID = id;
        Name = name;
        Description = description;
        Deadline = deadline;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public RealmList<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(RealmList<Task> tasks) {
        Tasks = tasks;
    }

    public RealmList<User> getMembers() {
        return Members;
    }

    public void setMembers(RealmList<User> members) {
        Members = members;
    }

    public String getDescription() {
        return Description;
    }

    public Member getAssigned() {
        return Assigned;
    }

    public void setAssigned(Member assigned) {
        Assigned = assigned;
    }

    public RealmList<String> getTags() {
        return Tags;
    }

    public void setTags(RealmList<String> tags) {
        Tags = tags;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public RealmList<Channel> getChannels() {
        return Channels;
    }

    public void setChannels(RealmList<Channel> channels) {
        Channels = channels;
    }
}

