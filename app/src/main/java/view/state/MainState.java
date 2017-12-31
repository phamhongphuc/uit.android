package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import app.App;
import app.Global;

public class MainState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();
    public final Global global = App.global;

    public final ObservableList<String> listString;
    public MainState() {
        listString = new ObservableArrayList<>();

        listString.clear();
        listString.add("một");
        listString.add("hai");
        listString.add("ba");
        listString.add("bốn");
        listString.add("năm");
    }
}