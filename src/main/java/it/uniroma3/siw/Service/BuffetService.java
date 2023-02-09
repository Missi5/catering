package it.uniroma3.siw.Service;

import java.util.LinkedList;
import java.util.List;


import it.uniroma3.siw.Model.Buffet;
import it.uniroma3.siw.Model.Chef;
import it.uniroma3.siw.Model.Piatto;
import it.uniroma3.siw.Repository.BuffetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BuffetService {

	@Autowired BuffetRepository buffetRepository;
	
	public List<Buffet> findAll(){
		List<Buffet> buffets = new LinkedList<Buffet>();
		for(Buffet b : this.buffetRepository.findAll())
			buffets.add(b);
		return buffets;
	}
	
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Buffet b) {
		this.buffetRepository.save(b);
	}

	
	@Transactional
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
	}


	@Transactional
	public void update(Buffet buffet) {
		// TODO Auto-generated method stub
		Buffet b = this.buffetRepository.findById(buffet.getId()).get();
		b.setNome(buffet.getNome());
		b.setDescrizione(buffet.getDescrizione());
		this.buffetRepository.save(b);
	}

	
	@Transactional
	public void addPiatto(Piatto dish, Long idBuffet) {
		// TODO Auto-generated method stub
		Buffet buffet = this.buffetRepository.findById(idBuffet).get();
		buffet.getPiatti().add(dish);
		this.buffetRepository.save(buffet);
	}



		

	
}
