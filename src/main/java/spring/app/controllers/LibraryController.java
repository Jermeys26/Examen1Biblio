package spring.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.app.entity.Library;
import spring.app.service.LibraryService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/librarys")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("librarys", libraryService.findAll());
        return "librarys";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("library", new Library());
        return "formLibrary";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Library library) {
        libraryService.store(library);
        return "redirect:/librarys";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Library l = libraryService.findById(id);
        model.addAttribute("library", l);
        return "updateLibrary";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Library library) {
        libraryService.update(library);
        return "redirect:/librarys";
    }

    @GetMapping("/delete/{id}")
    public String destroy(@PathVariable int id, Model model) {
        try {
            libraryService.destroy(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return show(model);
        }
        return "redirect:/librarys";
    }
}
