package it.uniroma3.siw.Repository;

import it.uniroma3.siw.Model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<Utente, Long> {

    @Query(value = "SELECT * FROM utente u Join credentials c ON (u.id = c.user_id) WHERE c.role = 'DEFAULT'", nativeQuery = true)
    public List<Utente> findClienti();

}
