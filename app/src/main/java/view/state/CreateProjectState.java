package view.state;

import android.databinding.ObservableField;

import module.object.User;

public class CreateProjectState {
    public final ObservableField<User> user = new ObservableField<>();

    public CreateProjectState() {
        ///Lay thong tin Nguoi dung de dua vao Assigned
//        user.set(OldGlobal.user.get());
    }
}
