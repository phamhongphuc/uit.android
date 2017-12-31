package object;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class Function {
    /// 1 lay ID, Ten, email tu socket


    ///3
    public static Project createProject(final int id, final User assigned,
                                        final String name, final String deadline,
                                        final String description, final RealmList<User> members) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Project newProject = new Project(id, name);
        realm.copyToRealm(newProject);
        realm.commitTransaction();
        return newProject;
    }

    public static Task createTask(final int id, final String name, final String deadline, final String description, final User assigned) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Task newTask = new Task(id, name, deadline, description, assigned);
        realm.copyToRealm(newTask);
        realm.commitTransaction();
        return newTask;
    }

    public static RealmResults<User> getMemberList(Project project, User user){
        /// Tru nguoi dang su dung ra, vi nguoi su dung de tren dau
        return project.getMembers()
                .where()
                .notEqualTo("id",user.getId())
                .findAll();
    }

}
