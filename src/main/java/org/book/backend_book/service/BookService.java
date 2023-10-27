package org.book.backend_book.service;

import org.book.backend_book.domain.Book;
import org.book.backend_book.repository.BookRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService( BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(final String id) {
        return bookRepository.findById(id);
    }

    public List<Book> findBooksByQuery(String query) {
        return bookRepository.getBooksByTitle(query);
    }

    public List<Book> searchBooksByPartialTitle(String title) {
        return bookRepository.findByPartialTitle(title);
    }

    public Book createBook(final Book book) {
        return bookRepository.save(book);
    }

}

