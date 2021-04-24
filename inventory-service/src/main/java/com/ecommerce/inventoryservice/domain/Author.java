package com.ecommerce.inventoryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID authorId;

    //@NotBlank(message = "Author name should not be blank")
    private String authorName;

    //@NotBlank(message = "Author image should not be blank")
    private String authorThumbnail;

    //@NotBlank(message = "Author description field should not be blank")
    private String authorBio;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private List<Book> authorBooksList = new ArrayList<>();

}
