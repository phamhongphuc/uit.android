package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class LoginState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();
    public final ObservableInt fragmentIndex = new ObservableInt();

    public LoginState() {

    }
}
