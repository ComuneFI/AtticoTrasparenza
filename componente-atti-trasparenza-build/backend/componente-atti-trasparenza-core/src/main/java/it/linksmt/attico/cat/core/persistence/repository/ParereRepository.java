package it.linksmt.attico.cat.core.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.linksmt.attico.cat.core.persistence.entity.Parere;

@Repository
public interface ParereRepository extends CrudRepository<Parere, Long> {

}
