package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Spring will detect this class as a Spring bean and create the class for us and wire it into the Spring Context
public class BookController {

    private BookRepository bookRepository;

    // Constructor for the book repository so when Spring creates this
    // it's going to automatically inject the instance of the book repository.
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // This method will be called when we get a request that comes into the "/books" URL.
    public String getBooks(Model model) { // The Spring MVC is going to pass in an instance of the model

        // Add an attribute to the model called "books" and that's going to be a list of books out of the book repository.
        // So underneath the covers bookRepository is using Spring Data JPA, that's going to use hibernate to get a list
        // of books out of the database.
        model.addAttribute("books", bookRepository.findAll());

        return "books"; // is going to tell Spring MVC to associate this with a view called books.
    }

}
