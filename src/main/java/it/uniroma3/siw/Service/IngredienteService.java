package it.uniroma3.siw.Service;

import java.util.LinkedList;
import java.util.List;


import it.uniroma3.siw.Model.Ingrediente;
import it.uniroma3.siw.Repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class IngredienteService {

	@Autowired private IngredienteRepository ingredienteRepository;
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredients = new LinkedList<Ingrediente>();
		for(Ingrediente i : this.ingredienteRepository.findAll())
			ingredients.add(i);
		return ingredients;
	}

	public Ingrediente findById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Ingrediente i) {
		this.ingredienteRepository.save(i);
	}
	
	@Transactional
	public void delete(Ingrediente i) {
		this.ingredienteRepository.delete(i);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.ingredienteRepository.deleteById(id);
	}

	@Transactional
	public void update(Ingrediente ingredient) {
		// TODO Auto-generated method stub
		Ingrediente i = this.ingredienteRepository.findById(ingredient.getId()).get();
		i.setNome(ingredient.getNome());
		i.setDescrizione(ingredient.getDescrizione());
		i.setOrigine(ingredient.getOrigine());
		this.ingredienteRepository.save(i);
	}
}
