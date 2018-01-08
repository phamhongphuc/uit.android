package object;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
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

    public Task(int id, String name, String deadline, String description, User assigned) {
        this.id = id;
        this.name = name;
        this.createdate = new Date();
        this.description = description;
        this.assigned = assigned;
        this.status = ONGOING;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.deadline = date;
    }

    ///Getter
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

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public long getDaysLeft() {
        long daysLeft = deadline.getTime() - (new Date()).getTime();
        return (daysLeft / (60 * 60 * 1000));
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}