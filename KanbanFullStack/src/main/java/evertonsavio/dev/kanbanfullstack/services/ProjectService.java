package evertonsavio.dev.kanbanfullstack.services;

import evertonsavio.dev.kanbanfullstack.domain.Backlog;
import evertonsavio.dev.kanbanfullstack.domain.Project;
import evertonsavio.dev.kanbanfullstack.domain.User;
import evertonsavio.dev.kanbanfullstack.exceptions.ProjectIdException;
import evertonsavio.dev.kanbanfullstack.exceptions.ProjectNotFoundException;
import evertonsavio.dev.kanbanfullstack.repositories.BacklogRepository;
import evertonsavio.dev.kanbanfullstack.repositories.ProjectRepository;
import evertonsavio.dev.kanbanfullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username){

        try{

            User user = userRepository.findByUsername(username);

            project.setUsers(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if(project.getId() != null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase()+" Already existis");
        }
    }

    public Project findProjectByIdentifier(String projectId, String username){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("Project "+ projectId +" does not existis");
        }
        //Principal e Project leader the same?
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project Not found in your account!");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId, String username){


        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }

}
