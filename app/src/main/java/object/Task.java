package object;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    public static final int ONGOING = 0;
    public static final int ONHOLD = 1;
    public static final int COMPLETE = 2;

    @PrimaryKey
    private int id;
    private String name;
    private Date createdate;
    private Date deadline;
    private String description;
    private User assigned;
    private RealmList<User> subscribers;
    private int status;


    public Task(String name, Date deadline, String description, User assigned) {
        this.name = name;
        this.deadline = deadline;
        createdate = new Date();
        this.description = description;
        this.assigned = assigned;
        this.status = ONGOING;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public User getAssigned() {
        return assigned;
    }

    public RealmList<User> getSubscribers() {
        return subscribers;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public void setSubscribers(RealmList<User> subscribers) {
        this.subscribers = subscribers;
    }

    public void setStatus(int status) {
        this.status = (status == ONGOING || status == COMPLETE) ? status : ONHOLD;
    }
}