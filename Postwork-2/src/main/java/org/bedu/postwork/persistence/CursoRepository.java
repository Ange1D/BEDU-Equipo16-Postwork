package org.bedu.postwork.persistence;

import org.bedu.postwork.model.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Long> {
}
