package com.bookstoreapi.controller;

import com.bookstoreapi.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    //Listing all Books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    //Adding New Book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setId(counter.incrementAndGet());
        books.add(book);
        return book;
    }

    //Updating Existing book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                return book;
            }
        }
        return null;
    }

    //Deleting an Existing Book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}

