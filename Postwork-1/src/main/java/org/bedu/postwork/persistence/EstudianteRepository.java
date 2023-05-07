package org.bedu.postwork.persistence;

import org.bedu.postwork.model.Estudiante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante,Long> {
}
