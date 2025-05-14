package spring.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.app.entity.Book;
import spring.app.entity.Library;
import spring.app.repository.LibraryRepository;
import spring.app.service.BookService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final LibraryRepository libraryRepository;

    public BookController(BookService bookService, LibraryRepository libraryRepository) {
        this.bookService = bookService;
        this.libraryRepository = libraryRepository;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("librarys", libraryRepository.findAll());
        return "formBook";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        bookService.store(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Book b = bookService.findById(id);

        if (b == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", b);
        model.addAttribute("librarys", libraryRepository.findAll());
        return "updateBook";
    }

    @PostMapping("/update")
    public String update(@RequestParam("libraryId") Integer libraryId, @ModelAttribute Book book,
            BindingResult result) {
        Library l = libraryRepository.findById(libraryId).orElse(null);
        book.setLibrary(l);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String destroy(@PathVariable int id, Model model) {
        try {
            bookService.destroy(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return show(model);
        }
        return "redirect:/books";
    }
}
