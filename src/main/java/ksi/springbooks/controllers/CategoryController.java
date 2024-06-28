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
import ksi.springbooks.models.Category;
import ksi.springbooks.services.CategoryService;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService service;

    @RequestMapping("/categories")
    public String viewCategoriesList(Model model) {
        List<Category> categories = service.findAll();
        model.addAttribute("categories", categories);
        return "categories_list";
    }

    @RequestMapping("/new_category")
    public String showFormNewCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "new_category";
    }

    @PostMapping("/save_category")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_category";
        }
        service.save(category);
        return "redirect:/categories";
    }

    @RequestMapping("/edit_category/{idc}")
    public String showEditFormCategory(@PathVariable("idc") Long idc, Model model) {
        Category category = service.findById(idc).orElse(null);
        model.addAttribute("category", category);
        return "edit_category";
    }

    @PostMapping("/update_category")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_category";
        }
        service.save(category);
        return "redirect:/categories";
    }

    @RequestMapping("/delete_category/{idc}")
    public String deleteCategory(@PathVariable("idc") Long idc) {
        service.deleteById(idc);
        return "redirect:/categories";
    }
}
