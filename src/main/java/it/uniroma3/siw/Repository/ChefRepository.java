package it.uniroma3.siw.Repository;

import java.util.List;

import it.uniroma3.siw.Model.Chef;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ChefRepository extends CrudRepository<Chef, Long> {
	


}
