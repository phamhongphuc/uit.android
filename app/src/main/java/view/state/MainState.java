package view.state;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import object.User;

public class MainState extends BaseObservable {
    public final ObservableField<String> status = new ObservableField<>();

    public MainState() { /**/ }
}