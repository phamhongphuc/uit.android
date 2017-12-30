package object;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Message extends RealmObject {
    @PrimaryKey
    private Date time;
    private User sender;
    private Channel idchannel;
    private String content;

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

    public Channel getIdchannel() {
        return idchannel;
    }

    public void setIdchannel(Channel idchannel) {
        this.idchannel = idchannel;
    }

    public String getContent() {
        return content;
    }
}

