package org.book.backend_book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDto {
    private String categoryId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}

