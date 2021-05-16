package com.ecommerce.orderservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID cargoId;

    @Min(value = 0,message = "Cargo price cannot be negative")
    private BigDecimal cargoPrice = BigDecimal.valueOf(9.99);

    @Future(message = "Please enter a valid delivery time")
    private Date cargoDeliveryTime;

    @OneToOne(mappedBy = "cargo")
    BookOrder bookOrder;
}
