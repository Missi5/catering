package it.uniroma3.siw.Repository;

import it.uniroma3.siw.Model.Utente;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Utente, Long> {
	

}
