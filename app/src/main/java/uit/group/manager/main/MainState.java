package uit.group.manager.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class MainState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();

    //    public final ObservableField<AccessToken> accessToken = new ObservableField<>();
    public MainState() {
//        status = ss;
    }
}

