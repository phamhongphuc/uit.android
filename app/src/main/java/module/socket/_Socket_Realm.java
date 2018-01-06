package module.socket;

import android.util.Log;

import java.util.ArrayList;

import object.Project;
import object.User;

public class _Socket_Realm {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void Pull(final User user, final User.Callback done) {
        final int[] queue = {0};

        final Project.Callback _project = new Project.Callback() {
            @Override
            public void Response(Project project) {
                Log.d("PROJECT NAME", project.getName());
                // Đã có project, chuẩn bị gọi thêm
                queue[0]--;
                if (queue[0] == 0) done.Response(user);
            }
        };

        final Project.CallbackProjects _projectIds = new Project.CallbackProjects() {
            @Override
            public void Response(ArrayList<Integer> projectIds) {
                queue[0] += projectIds.size();
                for (Integer projectId : projectIds) {
                    _Socket_Project.GetProjectById(projectId, _project);
                }
            }
        };

        final User.Callback responseUser = new User.Callback() {
            @Override
            public void Response(User user) {
                _Socket_Project.GetProjectIdsByUserId(user.getId(), _projectIds);
            }
        };

        responseUser.Response(user);
    }
}
