package org.book.backend_book.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "categories")
public class Category {
    @Id
    private String categoryId;
    private String name;
    private String description;
}

