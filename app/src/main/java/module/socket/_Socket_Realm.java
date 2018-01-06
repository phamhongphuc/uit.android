package module.socket;

import android.util.Log;

import java.util.ArrayList;

import module.callback.Count;
import module.callback.VoidCallback;
import object.Project;
import object.User;

public class _Socket_Realm {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void Pull(final User user, final VoidCallback done) {
        final Project.Callback _project = new Project.Callback() {
            @Override
            public void Response(Project project) {
                Log.d("PROJECT NAME", project.getName());
            }
        };

        final Project.CallbackProjects _projectIds = new Project.CallbackProjects() {
            @Override
            public void Response(ArrayList<Integer> projectIds, final VoidCallback projectsDone) {
                final Count count = new Count(projectIds.size(), projectsDone);
                for (Integer projectId : projectIds) {
                    _Socket_Project.GetProjectById(projectId, _project, count);
                }
            }
        };

        final User.Callback responseUser = new User.Callback() {
            @Override
            public void Response(final User user) {
                _Socket_Project.GetProjectIdsByUserId(user.getId(), _projectIds, new VoidCallback() {
                    @Override
                    public void Response() {
                        done.Response();
                    }
                });
            }
        };

        responseUser.Response(user);
    }
}