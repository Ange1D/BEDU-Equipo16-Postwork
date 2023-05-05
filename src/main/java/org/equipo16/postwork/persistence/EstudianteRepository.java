package org.equipo16.postwork.persistence;

import org.equipo16.postwork.model.Estudiante;
import org.springframework.data.repository.CrudRepository;

public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {
}
