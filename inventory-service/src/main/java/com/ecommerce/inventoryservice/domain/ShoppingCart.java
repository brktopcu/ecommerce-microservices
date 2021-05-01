package com.ecommerce.inventoryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID shoppingCartId;

    @Min(value = 0,message = "Toplam fiyat negatif olamaz")
    private BigDecimal totalPriceShoppingCart = new BigDecimal(0);

    private String shoppingCartName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "ShoppingCartBook",
            joinColumns ={@JoinColumn(name = "shopping_cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private List<Book> shoppingCartBooks = new ArrayList<>();

    /*@OneToOne(mappedBy = "shoppingCart")
    private BookOrder bookOrder;*/

    private UUID applicationUserId;
}
