package view.state;

import android.databinding.ObservableField;

import object.User;

public class CreateProjectState {
    public final ObservableField<User> user = new ObservableField<>();

    public CreateProjectState() {
        ///Lay thong tin Nguoi dung de dua vao Assigned
//        userId.set(OldGlobal.userId.get());
    }
}
