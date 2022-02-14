package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository= publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric= new Author("Eric", "Evans");
        Book ddd= new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod= new Author("Rod", "Johnson");
        Book noEJB= new Book("J2EE Development without EJB", "54845848");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


        Publisher peter= new Publisher();
        peter.setName("Peter Pan");
        peter.setAddressLine1("NeverLand 1");
        peter.setCity("NeverLand");
        peter.setZip("1");
        peter.setState("Land");
        peter.getBooks().add(ddd);
        peter.getBooks().add(noEJB);

        publisherRepository.save(peter);

        ddd.setPublisher(peter);
        bookRepository.save(ddd);

        System.out.println("Started in Bootstrap");

        System.out.println("Publisher Count: "+ publisherRepository.count());
        System.out.println("Publisher Number of Books: " +peter.getBooks().size());

        System.out.println("Number of Books: "+ bookRepository.count());

    }
}
