package com.ecommerce.inventoryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @NotBlank(message = "Kategori aciklamasi gereklidir")
    private String categoryDescription;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book_category",
            joinColumns={@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private List<Book> bookCategoryList =new ArrayList<>();
}
