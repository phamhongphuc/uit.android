package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class LoginState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();

    public LoginState() { /**/ }
}
