package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Spring will detect this class as a Spring bean and create the class for us and wire it into the Spring Context
public class AuthorController {

    private AuthorRepository authorRepository;

    // Constructor for the AuthorController so when Spring creates this
    // it's going to automatically inject the instance of the AuthorRepository.
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors") // This method will be called when we get a request that comes into the "/authors" URL.
    public String getAuthors(Model model) { // The Spring MVC is going to pass in an instance of the model

        // Add an attribute to the model called "authors" and that's going to be a list of authors out of the  authorRepository.
        // So underneath the covers authorRepository is using Spring Data JPA, that's going to use hibernate to get a list
        // of authors out of the database.
        model.addAttribute("authors", authorRepository.findAll());

        return "authors"; // is going to tell Spring MVC to associate this with a view called 'authors'.
    }

}
