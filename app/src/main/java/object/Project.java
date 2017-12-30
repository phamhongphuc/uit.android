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
/*
    public int getNumberSameStatusTasks(int Status){
        Task newTask = new Task(1,"",new Date(),"",this.assigned);
        int count=0;
        for(Task each:this.tasks){
            count+=(each.)
        }
    }
*/

    public RealmList<User> getMembers() {
        return members;
    }

    public String getDescription() {
        return description;
    }

    public User getAssigned() {
        return assigned;
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


    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addMembers(RealmList<User> members) {
        this.members.addAll(members);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }
}

