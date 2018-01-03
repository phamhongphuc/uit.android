package module.object;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private RealmList<User> members;
    private int status;
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
            obj.put("createdate", createdate);
            obj.put("deadline", deadline);
            obj.put("description", description);
            obj.put("assigned", assigned);
            obj.put("members", members);
            obj.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}