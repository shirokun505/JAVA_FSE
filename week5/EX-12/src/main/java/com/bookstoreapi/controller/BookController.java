package com.bookstoreapi.controller;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.*;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CollectionModel<EntityModel<BookDTO>>> getAllBooks() {
        List<EntityModel<BookDTO>> books = bookService.getAllBooks().stream()
                .map(this::addHATEOASLinks)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Books", String.valueOf(books.size()));

        CollectionModel<EntityModel<BookDTO>> collectionModel = CollectionModel.of(books);
        collectionModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withSelfRel());

        return new ResponseEntity<>(collectionModel, headers, HttpStatus.OK);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken (HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        if (book != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Book-Found", "true");
            return new ResponseEntity<>(addHATEOASLinks(book), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<CollectionModel<EntityModel<BookDTO>>> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<EntityModel<BookDTO>> books = bookService.filterBooks(title, author).stream()
                .map(this::addHATEOASLinks)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Filter-Results", "Filtered by title and Author");

        CollectionModel<EntityModel<BookDTO>> collectionModel = CollectionModel.of(books);
        collectionModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).filterBooks(title, author)).withSelfRel());

        return new ResponseEntity<>(collectionModel, headers, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Header", "Book Created Successfully");
        headers.add("Location", "/books/" + createdBook.getId());

        return new ResponseEntity<>(addHATEOASLinks(createdBook), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<BookDTO>> updateBook(@PathVariable Long id,@Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        if (updatedBook != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Book-Updated", "true");
            return new ResponseEntity<>(addHATEOASLinks(updatedBook), headers, HttpStatus.OK);
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

    // Helper method to add HATEOAS links
    private EntityModel<BookDTO> addHATEOASLinks(BookDTO bookDTO) {
        EntityModel<BookDTO> resource = EntityModel.of(bookDTO);

        // Add self link
        resource.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDTO.getId())
        ).withSelfRel());

        // Add link to all books
        resource.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()
        ).withRel("all-books"));

        return resource;
    }
}
