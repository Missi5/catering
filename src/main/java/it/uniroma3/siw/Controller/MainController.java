package it.uniroma3.siw.Controller;

import it.uniroma3.siw.Model.*;
import it.uniroma3.siw.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private ChefService chefService;

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private UserService userService;

    @GetMapping("/buffet")
    public String getBuffet(Model model) {
        model.addAttribute("buffet", buffetService.findAll());
        model.addAttribute("chef", chefService.findAll());
        model.addAttribute("piatti", piattoService.findAll());
        model.addAttribute("ingredienti", ingredienteService.findAll());
        model.addAttribute("newbuffet", new Buffet());
        model.addAttribute("newpiatto", new Piatto());
        model.addAttribute("newingrediente", new Ingrediente());
        return "buffet";
    }

    @GetMapping("/chef")
    public String getchef(Model model) {
        model.addAttribute("chef", chefService.findAll());
        return "Chef";
    }

    @GetMapping("/chef/{id}")
    public String specificchef(@PathVariable("id") Long id, Model model) {
        Chef c = chefService.findById(id);
        model.addAttribute("chef", c);
        model.addAttribute("buffet",c.getBuffets());

        return "/chefID";
    }

    @GetMapping("/admin/editbuffet/{id}")
    public String editBuffet(@PathVariable("id") Long id, Model model) {
        Buffet b = buffetService.findById(id);
        model.addAttribute("newbuffet", new Buffet());
        model.addAttribute("buffet", b);
        model.addAttribute("chef", chefService.findAll());
        List<Piatto> p = this.piattoService.findAll();
        List<Piatto> d = new ArrayList<>(p);

        d.removeAll(b.getPiatti());
        model.addAttribute("piatti", d);
        System.out.println(d);
        return "/admin/editbuffet";
    }

    @PostMapping(value = "/admin/editbuffet")
    public String editBuffet(@RequestParam Long id, @ModelAttribute("buffet") Buffet buffet, Model model, BindingResult userBindingResult) {
        Buffet b = this.buffetService.findById(id);
        b.setChef(buffet.getChef());
        b.setNome(buffet.getNome());
        b.setPiatti(buffet.getPiatti());
        if(!buffet.getDescrizione().isEmpty())
            b.setDescrizione(buffet.getDescrizione());
        this.buffetService.save(b);
        return "redirect:/buffet";
    }
    @GetMapping("/admin/chef")
    public String getBuffetadmin(Model model) {
        model.addAttribute("chef", chefService.findAll());
        model.addAttribute("newchef", new Chef());
        return "admin/Chef";
    }
    @GetMapping("/admin/clienti")
    public String getclientiadmin(Model model) {
        model.addAttribute("clienti", userService.getClienti());
        return "admin/Clienti";
    }

    @GetMapping("/start")
    public String Startup(Model model) {
        /*Chef c1 = new Chef("Filippo","Gustav","Tedesca");
        Ingrediente i1 = new Ingrediente();
        ArrayList<Ingrediente> l = new ArrayList<>();
        l.add(new Ingrediente("manzo","Argentina","buona"));
        Piatto p1 = new Piatto("Arrosto di manzo con patate al forno e carote","piatto elabora e squisito", l);
        l = new ArrayList<>();
        l.add(new Ingrediente("salsiccia","Italia","ono buona"));
        Piatto p2 = new Piatto("Salsicce di maiale con fagioli e cipolle","piatto buono",l);
        Set<Piatto> lp = new HashSet<>();
        lp.add(p1);
        lp.add(p2);
        Buffet b1 = new Buffet("Buffet di carne","Questo buffet offre una selezione di piatti caldi e succulenti con una varietà di opzioni di carne, dalla carne bovina alla carne di maiale e di pollo.",c1, lp);
    */
        Chef c2 = new Chef("Emanuel","Carri","Giapponese");
        Ingrediente i2 = new Ingrediente();
        ArrayList<Ingrediente> l2 = new ArrayList<>();
        l2.add(new Ingrediente("Salmone","Japan","buona"));
        Piatto p3 = new Piatto("Sushi roll con salmone e avocado","piatto elabora e squisito", l2);
        l2 = new ArrayList<>();
        l2.add(new Ingrediente("Gamberetti","hawai","ono buona"));
        Piatto p4 = new Piatto("Pad Thai con gamberetti e verdure","piatto buono",l2);
        Set<Piatto> lp2 = new HashSet<>();
        lp2.add(p3);
        lp2.add(p4);
        Buffet b2 = new Buffet("Buffet internazionale","Questo buffet offre una selezione di piatti provenienti da diverse cucine del mondo, dalla cucina italiana alla cucina asiatica.",c2, lp2);


        buffetService.save(b2);
        return "buffet";
    }
    @PostMapping(value= "/newbuffet")
    public String newbuffet( @ModelAttribute("buffet") Buffet buffet,Model model) {
        this.buffetService.save(buffet);
        Chef c = this.chefService.findById(buffet.getChef().getId());
        c.getBuffets().add(buffet);
        this.chefService.save(c);
        return "redirect:/buffet";

    }

    @PostMapping(value= "/newpiatto")
    public String newpiatto( @ModelAttribute("piatto") Piatto piatto,Model model) {
        this.piattoService.save(piatto);
        return "redirect:/buffet";

    }
    @PostMapping(value= "/newingrediente")
    public String newingrediente( @ModelAttribute("ingrediente") Ingrediente ingrediente,Model model) {
        this.ingredienteService.save(ingrediente);
        return "redirect:/buffet";

    }

    @PostMapping(value= "/newchef")
    public String newchef( @ModelAttribute("chef") Chef chef,Model model) {
       this.chefService.save(chef);
        return "redirect:/admin/chef";

    }

    @PostMapping(value = "/admin/editChef")
    public String editChef(@RequestParam Long ChefId, @ModelAttribute("chef") Chef chef, Model model, BindingResult userBindingResult) {
        Chef c = this.chefService.findById(ChefId);
        c.setNome(chef.getNome());
        c.setCognome(chef.getCognome());
        c.setNazionalita(chef.getNazionalita());
        this.chefService.save(c);
        return "redirect:/admin/chef";
    }
    @PostMapping(value = "/admin/editClienti")
    public String editCliente(@RequestParam Long ClienteId, @ModelAttribute("clienti") Utente clienti, Model model, BindingResult userBindingResult) {
        Utente c = this.userService.getUser(ClienteId);
        c.setNome(clienti.getNome());
        c.setCognome(clienti.getCognome());
        this.userService.saveUser(c);
        return "redirect:/admin/clienti";
    }

    @GetMapping("/admin/deletechef/{chefId}")
    public String deleteChef(@PathVariable("chefId") Long chefId) {

        chefService.deleteById(chefId);

        return "redirect:/admin/chef";

    }

}
