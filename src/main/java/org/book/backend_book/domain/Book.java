package org.book.backend_book.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String title;
    private String subtitle;
    private String isbn;
    private List<String> authors;
    private String description;
    private String edition;
    private String publisher;
    private String categoryId;
}


