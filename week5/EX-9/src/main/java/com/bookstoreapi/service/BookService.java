package com.bookstoreapi.service;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.entity.Book;
import com.bookstoreapi.exception.ResourceNotFoundException;
import com.bookstoreapi.repository.BookRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    //Listing all Books
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    //Listing the Desired Book by Id
    public BookDTO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertEntityToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    }
//    //Creating a New Book
//    public BookDTO createBook(BookDTO bookDTO) {
//        Book book = convertDTOToEntity(bookDTO);
//        Book savedBook = bookRepository.save(book);
//        return convertEntityToDTO(savedBook);
//    }
//    //TO Update an Existing book
//    public BookDTO updateBook(Long id, BookDTO bookDTO) {
//        Book book = bookRepository.findById((id))
//                .orElseThrow(() -> new RuntimeException("Book Not Found"));
//        book.setTitle(bookDTO.getTitle());
//        book.setAuthor(bookDTO.getAuthor());
//        book.setPrice(bookDTO.getPrice());
//        Book updatedBook = bookRepository.save(book);
//        return convertEntityToDTO(updatedBook);
//    }

    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertDTOToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertEntityToDTO(savedBook);
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());

        try {
            Book updatedBook = bookRepository.save(book);
            return convertEntityToDTO(updatedBook);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Failed to update book. It may have been updated by another transaction.");
        }
    }
    //TO Delete a Book
    public void deleteBook(Long id) {
        Book book = bookRepository.findById((id))
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        bookRepository.delete(book);
    }
    //To find Book by author name and title name
    public List<BookDTO> filterBooks(String title, String author) {
        List<Book> books;
        if (title != null && author != null) {
            books = bookRepository.findByTitleContainingAndAuthorContaining(title, author);
        } else if (title != null) {
            books = bookRepository.findByTitleContaining(title);
        } else if (author != null) {
            books = bookRepository.findByAuthorContaining(author);
        } else {
            books = bookRepository.findAll();
        }
        return books.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private BookDTO convertEntityToDTO(Book book) {
        BookDTO bookDTO = this.modelMapper.map(book, BookDTO.class);
//        bookDTO.setId(book.getId());
//        bookDTO.setTitle(book.getTitle());
//        bookDTO.setAuthor(book.getAuthor());
//        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }

    private Book convertDTOToEntity(BookDTO bookDTO) {
        Book book = this.modelMapper.map(bookDTO, Book.class);

//        book.setId(bookDTO.getId());
//        book.setTitle(bookDTO.getTitle());
//        book.setAuthor(bookDTO.getAuthor());
//        book.setPrice(bookDTO.getPrice());
        return book;
    }
}
