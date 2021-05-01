package com.ecommerce.inventoryservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookId;

    private Integer totalRate=0;
    private Integer commentCount=0;
    private Integer orderSize=0;

    @Digits(integer = 1,fraction = 1)
    private Float bookRate=0.0F;

    @NotBlank(message = "Kitap adi gereklidir")
    private String bookName;

    private String publisherName;

    private String publishedDate;

    private String bookPdfDownloadLink;

    private String bookBuyLink;

    @NotNull(message = "Sayfa sayisi gereklidir")
    private Integer bookPage;

    //@NotNull(message = "Kitap fiyati gereklidir")
    //@Min(value = 0,message = "Fiyat negatif olamaz")
    private BigDecimal bookPrice;

    //@NotNull(message = "Stok alani gereklidir")
    private Long bookStock;

    @Lob
    private String bookDescription;


    //@NotNull(message = "Kitap resmi gereklidir")
    @Lob
    private String bookThumbnail;


    @ManyToMany(mappedBy = "authorBooksList",cascade = CascadeType.ALL)
    private List<Author> bookAuthorsList =new ArrayList<>();

    @ManyToMany(mappedBy = "shoppingCartBooks",cascade = CascadeType.ALL)
    private List<ShoppingCart> bookShoppingCart = new ArrayList<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Comment> bookCommentList = new ArrayList<>();

    @ManyToMany(mappedBy = "bookCategoryList",cascade = CascadeType.ALL)
    private List<Category> categoryBooksList =new ArrayList<>();

    @ManyToOne
    private Sale sale;

    private UUID applicationUserId;

}