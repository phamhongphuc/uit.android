package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;

public class LoginState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();
    public final ObservableField<Color> color = new ObservableField<>();

    public final ObservableInt viewPagerIndex = new ObservableInt(0);
    public final ObservableInt viewPagerCount = new ObservableInt(0);

    public LoginState() {
    }
}
