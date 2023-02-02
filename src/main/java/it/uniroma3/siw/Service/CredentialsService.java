package it.uniroma3.siw.Service;

import java.util.Optional;


import it.uniroma3.siw.Model.Credentials;
import it.uniroma3.siw.Repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class CredentialsService {
	
	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	public Credentials findByUsername(String username) {
		Optional<Credentials> credentials = credentialsRepository.findByUsername(username);

		if (credentials.isPresent()) {
			return credentials.get();
		}

		return null;
	}

	
	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		if (credentials.getRole() == null) {
			credentials.setRole(Credentials.DEFAULT_ROLE);
		}
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return credentialsRepository.save(credentials);
	}


	}

