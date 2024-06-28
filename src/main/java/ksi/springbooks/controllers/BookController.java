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
import org.springframework.web.servlet.ModelAndView;
import ksi.springbooks.models.Book;
import ksi.springbooks.models.Category;
import ksi.springbooks.models.Publisher;
import ksi.springbooks.models.Author;
import ksi.springbooks.services.BookService;
import ksi.springbooks.services.CategoryService;
import ksi.springbooks.services.PublisherService;
import ksi.springbooks.services.AuthorService;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/books_list")
    public String viewBooksList(Model model) {
        List<Book> lb = bookService.findAll();
        model.addAttribute("lb", lb);
        return "books_list";
    }

    @RequestMapping("/new_book")
    public String showFormNewBook(Model model) {
        Book nb = new Book();
        List<Publisher> publishers = publisherService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Author> authors = authorService.findAll();
        model.addAttribute("book", nb);
        model.addAttribute("publishers", publishers);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        return "new_book";
    }

    @PostMapping("/save_book")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Publisher> publishers = publisherService.findAll();
            List<Category> categories = categoryService.findAll();
            List<Author> authors = authorService.findAll();
            model.addAttribute("publishers", publishers);
            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
            return "new_book";
        }
        bookService.save(book);
        return "redirect:/books_list";
    }

    @RequestMapping("/edit_book/{idb}")
    public ModelAndView showEditFormBook(@PathVariable(name = "idb") Long idb) {
        ModelAndView mav = new ModelAndView("edit_book");
        Book book = bookService.findById(idb).orElse(null);
        List<Publisher> publishers = publisherService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Author> authors = authorService.findAll();
        mav.addObject("book", book);
        mav.addObject("publishers", publishers);
        mav.addObject("categories", categories);
        mav.addObject("authors", authors);
        return mav;
    }

    @PostMapping("/update_book")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Publisher> publishers = publisherService.findAll();
            List<Category> categories = categoryService.findAll();
            List<Author> authors = authorService.findAll();
            model.addAttribute("publishers", publishers);
            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
            return "edit_book";
        }
        bookService.save(book);
        return "redirect:/books_list";
    }

    @RequestMapping("/delete_book/{idb}")
    public String deleteBook(@PathVariable(name = "idb") Long idb) {
        bookService.deleteById(idb);
        return "redirect:/books_list";
    }
}
