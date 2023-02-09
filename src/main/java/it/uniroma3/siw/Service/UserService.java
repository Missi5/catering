package it.uniroma3.siw.Service;

import java.util.List;
import java.util.Optional;


import it.uniroma3.siw.Model.Utente;
import it.uniroma3.siw.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class UserService {
	
	@Autowired private UserRepository userRepository;
	
	public Utente getUser(Long id) {
		
		/* Optional in java e' un container di object che possono essere
		 * anche null... fornisce metodi per gestire il caso null in modo
		 * semplice e rapido...												
		 * */
        Optional<Utente> result = this.userRepository.findById(id);
        return result.orElse(null);
	}
	
	@Transactional
	public Utente saveUser(Utente user) {
		return this.userRepository.save(user);
	}

	@Transactional
	public List<Utente> getClienti() {
		return userRepository.findClienti();
	}
}
