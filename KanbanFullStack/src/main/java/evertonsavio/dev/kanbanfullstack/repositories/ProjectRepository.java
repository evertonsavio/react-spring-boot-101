package evertonsavio.dev.kanbanfullstack.repositories;

import evertonsavio.dev.kanbanfullstack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
