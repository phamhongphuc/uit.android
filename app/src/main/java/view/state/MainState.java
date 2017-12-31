package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import app.App;
import app.Global;

public class MainState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();
    public final Global global = App.global;

    public MainState() {
    }
}