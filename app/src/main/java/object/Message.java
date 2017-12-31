package object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Message extends RealmObject {
    @PrimaryKey
    private long id;
    private Date time;
    private User sender;
    private Channel channel;
    private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("time", time);
            obj.put("sender", sender);
            obj.put("channel", channel);
            obj.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

