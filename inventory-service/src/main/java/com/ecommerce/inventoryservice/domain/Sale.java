package com.ecommerce.inventoryservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID saleId;

    @Min(value = 0,message = "Indirim orani negatif olamaz")
    private Integer discountRate;

    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL)
    List<Book> bookSale = new ArrayList<>();
}
