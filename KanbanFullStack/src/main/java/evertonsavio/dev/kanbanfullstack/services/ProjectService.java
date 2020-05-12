package evertonsavio.dev.kanbanfullstack.services;

import evertonsavio.dev.kanbanfullstack.domain.Project;
import evertonsavio.dev.kanbanfullstack.exceptions.ProjectIdException;
import evertonsavio.dev.kanbanfullstack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase()+" Already existis");
        }


    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("Project "+ projectId +" does not existis");
        }

        return project;
    }
}
