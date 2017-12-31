package app;

import android.databinding.ObservableField;

import object.Project;
import object.User;

/**
 * Created by SofiaJetson on 12/31/2017.
 */

public class Global {
    public static ObservableField<User> user = new ObservableField<>();
    public static ObservableField<Project> project = new ObservableField<>();

}
