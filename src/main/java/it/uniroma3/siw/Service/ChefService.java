package it.uniroma3.siw.Service;

import java.util.LinkedList;
import java.util.List;


import it.uniroma3.siw.Model.Buffet;
import it.uniroma3.siw.Model.Chef;
import it.uniroma3.siw.Repository.ChefRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChefService {

	@Autowired ChefRepository chefRepository;
	
	public List<Chef> findAll(){
		List<Chef> chefs = new LinkedList<Chef>();
		for(Chef b : this.chefRepository.findAll())
			chefs.add(b);
		return chefs;
	}
	
	
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Chef b) {
		this.chefRepository.save(b);
	}

	@Transactional
	public void deleteById(Long id) {
		this.chefRepository.deleteById(id);
	}

	@Transactional
	public void update(Chef chef) {
		// TODO Auto-generated method stub
		Chef c = this.chefRepository.findById(chef.getId()).get();
		c.setNome(chef.getNome());
		c.setCognome(chef.getCognome());
		c.setNazionalita(chef.getNazionalita());
		this.chefRepository.save(c);
	}

	@Transactional
	public void addBuffet(Long idChef, Buffet buffet) {
		// TODO Auto-generated method stub
		Chef chef = this.chefRepository.findById(idChef).get();
		chef.getBuffets().add(buffet);
		buffet.setChef(chef);
		this.chefRepository.save(chef);
	}




}
