package ksi.springbooks.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ksi.springbooks.models.Publisher;
import ksi.springbooks.services.PublisherService;

import java.util.List;

@Controller
public class PublisherController {
    @Autowired
    private PublisherService service;

    @RequestMapping("/publishers")
    public String viewPublishersList(Model model) {
        List<Publisher> publishers = service.findAll();
        model.addAttribute("publishers", publishers);
        return "publishers_list";
    }

    @RequestMapping("/new_publisher")
    public String showFormNewPublisher(Model model) {
        Publisher publisher = new Publisher();
        model.addAttribute("publisher", publisher);
        return "new_publisher";
    }

    @PostMapping("/save_publisher")
    public String savePublisher(@Valid @ModelAttribute("publisher") Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_publisher";
        }
        service.save(publisher);
        return "redirect:/publishers";
    }

    @RequestMapping("/edit_publisher/{idp}")
    public String showEditFormPublisher(@PathVariable("idp") Long idp, Model model) {
        Publisher publisher = service.findById(idp).orElse(null);
        model.addAttribute("publisher", publisher);
        return "edit_publisher";
    }

    @PostMapping("/update_publisher")
    public String updatePublisher(@Valid @ModelAttribute("publisher") Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_publisher";
        }
        service.save(publisher);
        return "redirect:/publishers";
    }

    @RequestMapping("/delete_publisher/{idp}")
    public String deletePublisher(@PathVariable("idp") Long idp) {
        service.deleteById(idp);
        return "redirect:/publishers";
    }
}
