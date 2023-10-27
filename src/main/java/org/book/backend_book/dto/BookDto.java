package org.book.backend_book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BookDto {
    private String id;

    @NotBlank
    private String title;
    private String subtitle;
    private String isbn;
    private List<String> authors;
    private String description;
    private String edition;
    private String publisher;
    private String categoryId;
}
