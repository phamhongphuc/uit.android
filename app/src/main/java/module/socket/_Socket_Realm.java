package module.socket;

import java.util.ArrayList;

import module.callback.Count;
import module.callback.VoidCallback;
import object.Project;
import object.User;

public class _Socket_Realm {
    private static final io.socket.client.Socket socket = _Socket.getSocket();

    public static void Pull(final User user, final VoidCallback afterPull) {
        final Project.CallbackProjects responseProjectIds = new Project.CallbackProjects() {
            @Override
            public void Response(ArrayList<Integer> projectIds, final VoidCallback afterGetProjectIds) {
                final Count countProjects = new Count(projectIds.size(), afterGetProjectIds);
                final Project.Callback responseProject = new Project.Callback() {
                    @Override
                    public void Response(final Project project) {
                        final User.CallbackUsers responseUserIds = new User.CallbackUsers() {
                            @Override
                            public void Response(ArrayList<String> userIds, VoidCallback afterGetProjectMember) {
                                final Count countUsers = new Count(userIds.size(), afterGetProjectMember);
                                final User.Callback responseUser = new User.Callback() {
                                    @Override
                                    public void Response(final User user) {
                                        project.addMember(user);
                                        countUsers.decrease();
                                    }
                                };

                                for (String userId : userIds) {
                                    _Socket_User.GetUserById(userId, responseUser, countUsers);
                                }
                            }
                        };
                        _Socket_Project.GetProjectMemberIdsByProjectId(project.getId(), responseUserIds, new VoidCallback() {
                            @Override
                            public void Response() {
                                countProjects.decrease();
                            }
                        });
                    }
                };

                for (Integer projectId : projectIds) {
                    _Socket_Project.GetProjectById(projectId, responseProject);
                }
            }
        };
        _Socket_Project.GetProjectIdsByUserId(user.getId(), responseProjectIds, new VoidCallback() {
            @Override
            public void Response() {
                afterPull.Response();
            }
        });
    }
}