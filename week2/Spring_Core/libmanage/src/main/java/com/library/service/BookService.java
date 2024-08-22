package com.library.service;

import java.util.ArrayList;

import org.springframework.lang.NonNull;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName){
        if (bookName!=null && !isBookInList(bookName))
            bookRepository.addBook(bookName);
    }

    public void removeBook(String bookName){
        if (bookName!=null && isBookInList(bookName))
            bookRepository.removeBook(bookName);
    }

    public ArrayList<String> getBookList(){
        return bookRepository.getList();
    }

    boolean isBookInList(@NonNull String bookname){
        for (String book: bookRepository.getList()){
            if (book.equals(bookname))
                return true;
        }
        return false;
    }

}
