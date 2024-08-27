package com.bookstoreapi.controller;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Books", String.valueOf(books.size()));
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        if (book != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Book-Found", "true");
            return new ResponseEntity<>(book, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookDTO>> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<BookDTO> books = bookService.filterBooks(title, author);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Filter-Results", "Filtered by title and Author");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Header", "Book Created Successfully");
        headers.add("Location", "/books/" + createdBook.getId());
        return new ResponseEntity<>(createdBook, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,@Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        if (updatedBook != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Book-Updated", "true");
            return new ResponseEntity<>(updatedBook, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Book-Deleted", "true");
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

//package com.bookstoreapi.controller;
//
//import com.bookstoreapi.dto.BookDTO;
//import com.bookstoreapi.service.BookService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/books")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//    @GetMapping
//    public List<BookDTO> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @GetMapping("/{id}")
//    public BookDTO getBookById(@PathVariable Long id) {
//        return bookService.getBookById(id);
//    }
//
//    @PostMapping
//    public BookDTO createBook(@Valid @RequestBody BookDTO bookDTO) {
//        return bookService.createBook(bookDTO);
//    }
//
//    @PutMapping("/{id}")
//    public BookDTO updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
//        return bookService.updateBook(id, bookDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable Long id) {
//        bookService.deleteBook(id);
//    }
//}
