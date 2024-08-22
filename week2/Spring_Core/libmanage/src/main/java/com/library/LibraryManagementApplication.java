package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.repository.BookRepository;
import com.library.service.BookService;

/*

        NOTE - this Library Management app is for Exercise 1-3

 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookService bookService = (BookService) context.getBean(BookService.class);
            bookService.setBookRepository((BookRepository) context.getBean(BookRepository.class));
            bookService.addBook("Echoes of Eternity");
            bookService.addBook("The Quantum Enigma");
            bookService.addBook("The Quantum Enigma");
            bookService.addBook(null);
            bookService.addBook("Whispers of the Forgotten Realm");
            bookService.addBook("The Celestial Chronicles");
            bookService.removeBook("Shadows of the Lost City");
            bookService.removeBook("The Celestial Chronicles");
            System.out.println("\nBook List:-\n");
            for (String book : bookService.getBookList()) {
                System.out.printf("\n%s\n\n",book);
            }
        }
    }
}
