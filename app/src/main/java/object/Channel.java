package object;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.Date;

import io.realm.ChannelRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
@Parcel(implementations = {ChannelRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Channel.class})
public class Channel extends RealmObject {
    @LinkingObjects("channel")
    private final RealmResults<Message> messages = null;
    @PrimaryKey
    private String id;
    private String name;
    private User assigned;
    private RealmList<User> members;
    private Project project;
    private Date createdate;
    private Date lastupdate;

    public Channel() {
    }

    public Channel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public RealmList<User> getMembers() {
        return members;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("assigned", assigned);
            obj.put("members", members);
            obj.put("createdate", createdate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
