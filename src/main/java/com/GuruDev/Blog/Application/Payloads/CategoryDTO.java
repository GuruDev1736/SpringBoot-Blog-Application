package com.GuruDev.Blog.Application.Payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private int categoryId;

    @NotEmpty
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;
}
