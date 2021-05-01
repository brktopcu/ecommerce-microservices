package com.ecommerce.inventoryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID commentId;


    @NotBlank(message = "Yorum basligi gereklidir")
    private String commentTitle;

    @NotBlank(message = "Yorum aciklamasi gereklidir")
    private String commentDescription;

    @NotBlank(message = "Puan alani gereklidir")
    @Range(min = 0,max = 5,message = "Lutfen uygun bir puan araligi girin")
    private Integer rate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    private UUID applicationUserId;
}
