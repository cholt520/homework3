package com.example.book.service.impl;


import com.example.book.dao.BookAuthorRepository;
import com.example.book.entities.BookAuthor;
import com.example.book.entities.BookAuthorId;
import com.example.book.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    @Autowired
    private BookAuthorRepository bookAuthorRepository;


    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorRepository.findAll();
    }

    public BookAuthor createBookAuthor(BookAuthor bookAuthor) {
        return bookAuthorRepository.save(bookAuthor);
    }

    public void deleteBookAuthor(BookAuthorId bookAuthorId) {
        bookAuthorRepository.deleteById(bookAuthorId);
    }
}
