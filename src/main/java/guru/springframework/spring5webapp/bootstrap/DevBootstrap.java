package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// We wont use this in PRODUCTION!
// JUST FOR DEVELOPMENT. We will add something later to prevent this from running in proudction.

@Component // This makes a Spring Bean, so now this would get wired up by the Spring Context
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    // We can use @Autowired or we can create a constructor to have the repositories injected.
    // This class will get created and managed by the Spring Framework and the Author and Book
    // Repository (which we are going to get an implemention of from Spring Data JPA) are going
    // to get autowired into this class.
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harperCollins = new Publisher("Harper Collins", "Rivadavia 323");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(harperCollins);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx", "Corrientes 124");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(worx);
        bookRepository.save(noEJB);
    }

}
