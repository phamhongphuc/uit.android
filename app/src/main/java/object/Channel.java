package object;

import java.lang.reflect.Member;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Channel extends RealmObject {
    @PrimaryKey
    private String ID;
    private String Name;
    private Member Asssigned;
    private RealmList<User> Members;
    private Date CreateDate;

    public Channel(String id, String name) {
        ID = id;
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Member getAsssigned() {
        return Asssigned;
    }

    public void setAsssigned(Member asssigned) {
        Asssigned = asssigned;
    }

    public RealmList<User> getMembers() {
        return Members;
    }

    public void setMembers(RealmList<User> members) {
        Members = members;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
