package it.uniroma3.siw.Service;

import java.util.LinkedList;
import java.util.List;


import it.uniroma3.siw.Model.Ingrediente;
import it.uniroma3.siw.Model.Piatto;
import it.uniroma3.siw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class PiattoService {
	
	@Autowired private PiattoRepository piattoRepository;

	public List<Piatto> findAll(){
		List<Piatto> dishs = new LinkedList<Piatto>();
		for(Piatto p : this.piattoRepository.findAll())
			dishs.add(p);
		return dishs;
	}

	public Piatto findById(Long id) {
		return this.piattoRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Piatto b) {
		this.piattoRepository.save(b);
	}
	
	@Transactional
	public void delete(Piatto b) {
		this.piattoRepository.delete(b);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.piattoRepository.deleteById(id);
	}


	public void update(Piatto piatto) {
		// TODO Auto-generated method stub
		Piatto p = this.piattoRepository.findById(piatto.getId()).get();
		p.setNome(piatto.getNome());
		p.setDescrizione(piatto.getDescrizione());
		this.piattoRepository.save(p);
		
	}

	public void addIngredient(Ingrediente ingredient, Long idpiatto) {
		// TODO Auto-generated method stub
		Piatto p = this.piattoRepository.findById(idpiatto).get();
		p.getIngredienti().add(ingredient);
		this.save(p);
	}	
	
}
