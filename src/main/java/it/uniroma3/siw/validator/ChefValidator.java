package it.uniroma3.siw.validator;

import it.uniroma3.siw.Model.Chef;
import it.uniroma3.siw.Service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Component
public class ChefValidator implements Validator {

	@Autowired private ChefService chefService;

	@Override
	public boolean supports(Class<?> clazz) {
		Chef.class.equals(clazz);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		//if(this.chefService.alreadyExist((Chef)target))
			//errors.reject("chef.duplicato");

	}

}
