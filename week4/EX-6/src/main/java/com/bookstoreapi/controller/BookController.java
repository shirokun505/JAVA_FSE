package com.bookstoreapi.controller;

import com.bookstoreapi.entity.Book;
import com.bookstoreapi.exception.BookNotFoundException;
import com.bookstoreapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        book.setId(counter.incrementAndGet());
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Header", "Book Created!!")
                .body(book);
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

    //Getting desired book by Id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        return ResponseEntity.ok()
                .header("Header", "Book Retrieved!!")
                .body(book);
    }

    //Retrieving books by Author Name or Book Title
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                        (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .toList();
    }


}

