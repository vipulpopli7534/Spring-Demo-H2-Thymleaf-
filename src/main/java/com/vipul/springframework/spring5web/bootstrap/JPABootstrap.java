package com.vipul.springframework.spring5web.bootstrap;

import com.vipul.springframework.spring5web.dataservice.AuthorService;
import com.vipul.springframework.spring5web.dataservice.BookService;
import com.vipul.springframework.spring5web.dataservice.PublisherService;
import com.vipul.springframework.spring5web.model.Author;
import com.vipul.springframework.spring5web.model.Book;
import com.vipul.springframework.spring5web.model.Publisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class JPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;

    public JPABootstrap(AuthorService authorService, BookService bookService, PublisherService publisherService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeData();
    }

    private void initializeData() {
        Author eric = new Author("Vipul", "popli");
        Publisher publisher = new Publisher("dharma", "mumbai");
        publisherService.save(publisher);  /*mandatory to add the child element in DB prior to the parent ele in OnetoOne or OnetoMany*/
        Book  ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorService.save(eric);
        bookService.save(ddd);


        //Rod
        Author rod = new Author("kanika", "popli");
        Publisher publisher2 = new Publisher("Akrosh", "Delhi");
        publisherService.save(publisher2);
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher2);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorService.save(rod);
        bookService.save(noEJB);
    }
}
