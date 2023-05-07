package org.bedu.postwork.persistence;

import org.bedu.postwork.model.Materia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends CrudRepository<Materia,Long> {
}
