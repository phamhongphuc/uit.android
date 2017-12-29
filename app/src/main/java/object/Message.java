package object;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Message extends RealmObject {
    @PrimaryKey
    private Date Time;
    private User Sender;
    private Channel IDChannel;
    private String Content;

    public Message(String content) {
        Content = content;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public User getSender() {
        return Sender;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public Channel getIDChannel() {
        return IDChannel;
    }

    public void setIDChannel(Channel IDChannel) {
        this.IDChannel = IDChannel;
    }

    public String getContent() {
        return Content;
    }
}

