package it.uniroma3.siw.Repository;

import java.util.Optional;

import it.uniroma3.siw.Model.Credentials;
import org.springframework.data.repository.CrudRepository;


public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

    public Optional<Credentials> findByUsername(String username);

}
