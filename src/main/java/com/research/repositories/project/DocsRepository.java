package com.research.repositories.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.research.entity.Docs;
import com.research.entity.Project;
import com.research.repositories.BaseRepository;

public interface DocsRepository extends BaseRepository<Docs> {
List<Docs> findByProjectId(Project projectId);

@Query("select count(d) from Docs d where d.projectId.id = ?")
Long countByProject(Long id);
}
