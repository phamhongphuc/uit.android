package view.state;

import android.databinding.ObservableField;

import app.Global;
import object.User;

/**
 * Created by SofiaJetson on 12/31/2017.
 */

public class CreateProjectState {
    public final ObservableField<User> user = new ObservableField<>();

    public CreateProjectState() {
        ///Lay thong tin Nguoi dung de dua vao Assigned
        user.set(Global.user.get());
    }
}
