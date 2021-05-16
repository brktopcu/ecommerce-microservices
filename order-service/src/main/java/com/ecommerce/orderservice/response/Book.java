package com.ecommerce.orderservice.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Book {
    private UUID bookId;
    private Integer totalRate=0;
    private Integer commentCount=0;
    private Integer orderSize=0;
    private Float bookRate=0.0F;
    private String bookName;
    private String publisherName;
    private String publishedDate;
    private String bookPdfDownloadLink;
    private String bookBuyLink;
    private Integer bookPage;
    private BigDecimal bookPrice;
    private Long bookStock;
    private String bookDescription;
    private String bookThumbnail;

}
