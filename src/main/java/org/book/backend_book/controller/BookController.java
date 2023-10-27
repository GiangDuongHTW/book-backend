package org.book.backend_book.controller;

import jakarta.validation.Valid;
import org.book.backend_book.domain.Book;
import org.book.backend_book.dto.BookDto;
import org.book.backend_book.repository.BookRepository;
import org.book.backend_book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream().map(this::toDto).collect(toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(this::toDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Book> findBooksByQuery(@RequestParam String query) {
        return bookService.findBooksByQuery(query);
    }

    @GetMapping("/searchByPartialTitle")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String partialTitle) {
        if (partialTitle == null || partialTitle.trim().isEmpty()) {
            return ResponseEntity.noContent().build();// Return an empty list if partialTitle is empty
        }

        List<Book> books = bookService.searchBooksByPartialTitle(partialTitle);

        if (books.isEmpty() || !containsValidMatch(books, partialTitle)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(books);
    }


    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto book) {
        Book createdBook = bookService.createBook(fromDto(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(createdBook));
    }

    private Book fromDto(BookDto book) {
        return Book.builder()
                .id(book.getId())
                .categoryId(book.getCategoryId())
                .description(book.getDescription())
                .authors(book.getAuthors())
                .edition(book.getEdition())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .subtitle(book.getSubtitle())
                .title(book.getTitle())
                .build();
    }

    private BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .categoryId(book.getCategoryId())
                .description(book.getDescription())
                .authors(book.getAuthors())
                .edition(book.getEdition())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .subtitle(book.getSubtitle())
                .title(book.getTitle())
                .build();
    }

    private boolean containsValidMatch(List<Book> books, String title) {
        return books.stream().anyMatch(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()));
    }
}


