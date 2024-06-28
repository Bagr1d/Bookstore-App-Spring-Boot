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
import ksi.springbooks.models.Author;
import ksi.springbooks.services.AuthorService;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService service;

    @RequestMapping("/authors_list")
    public String viewAuthorsList(Model model) {
        List<Author> authors = service.findAll();
        model.addAttribute("authors", authors);
        return "authors_list";
    }

    @RequestMapping("/new_author")
    public String showFormNewAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "new_author";
    }

    @PostMapping("/save_author")
    public String saveAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_author";
        }
        service.save(author);
        return "redirect:/authors_list";
    }

    @RequestMapping("/edit_author/{ida}")
    public String showEditFormAuthor(@PathVariable("ida") Long ida, Model model) {
        Author author = service.findById(ida).orElse(null);
        model.addAttribute("author", author);
        return "edit_author";
    }

    @PostMapping("/update_author")
    public String updateAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_author";
        }
        service.save(author);
        return "redirect:/authors_list";
    }

    @RequestMapping("/delete_author/{ida}")
    public String deleteAuthor(@PathVariable("ida") Long ida) {
        service.deleteById(ida);
        return "redirect:/authors_list";
    }
}
