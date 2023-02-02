package it.uniroma3.siw.Controller;



import it.uniroma3.siw.Model.Credentials;
import it.uniroma3.siw.Model.Utente;
import it.uniroma3.siw.Service.CredentialsService;
import it.uniroma3.siw.validator.CredentialsValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class AuthenticationController {

	@Autowired private CredentialsService credentialsService;
	@Autowired private CredentialsValidator credentialsValidator;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "Register";
	}
	

	@PostMapping(value= "/register")
	public String registerUser( @ModelAttribute("user") Utente user,
								BindingResult userBindingResult, 
							     @ModelAttribute("credentials") Credentials credentials,
							    BindingResult credentialsBindingResult,
							    Model model) {
		
        // validazione user e credenziali
        //this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors() ) {
			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "redirect:/";
		}
		model.addAttribute("user", user);
		model.addAttribute("credentials", credentials);		
		return "Register";

	}
	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
		Utente utente = credentials.getUser();
		model.addAttribute("user", utente);

		//se e' admin
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "admin/home";
		}
		//se non lo e'
		return "clienti/home";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "redirect:/";
	}
	

	
}
