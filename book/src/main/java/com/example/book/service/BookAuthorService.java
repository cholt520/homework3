package com.example.book.service;


import com.example.book.entities.BookAuthor;
import com.example.book.entities.BookAuthorId;

import java.util.List;

public interface BookAuthorService {

    public List<BookAuthor> getAllBookAuthors();

    public BookAuthor createBookAuthor(BookAuthor bookAuthor) ;

    public void deleteBookAuthor(BookAuthorId bookAuthorId);
}
