package app;

import android.databinding.ObservableField;

import object.Project;
import object.User;

public class Global {
    public static ObservableField<User> user = new ObservableField<>();
    public static ObservableField<Project> project = new ObservableField<>();
}
