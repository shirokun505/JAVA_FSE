package com.library.repository;

import java.util.ArrayList;

import org.springframework.lang.NonNull;


public class BookRepository {
    private final ArrayList<String> bookList;

    public BookRepository(){
        bookList = new ArrayList<>();
    }

    public void addBook(@NonNull String bookName){
        bookList.add(bookName);
    }

    public void removeBook(@NonNull String bookName){
        for(String book: bookList){
            if (bookName.equals(book)){
                bookList.remove(book);
                System.out.println("\nBook removed -"+bookName+"!\n");
                return;
            }
        }
    }

    public ArrayList<String> getList(){
        return bookList;
    }

}
