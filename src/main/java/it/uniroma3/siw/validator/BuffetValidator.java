package it.uniroma3.siw.validator;

import it.uniroma3.siw.Model.Buffet;
import it.uniroma3.siw.Service.BuffetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Component
public class BuffetValidator implements Validator {

	@Autowired private BuffetService buffetService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	//	if(this.buffetService.alreadyExist((Buffet)target)) {
				//errors.reject("buffet.duplicato");
		}

	}


