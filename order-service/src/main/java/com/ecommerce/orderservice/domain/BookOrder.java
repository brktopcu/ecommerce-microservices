package com.ecommerce.orderservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookOrderId;

    private String username;

    @NotBlank(message = "Alıcı adı zorunludur")
    private String buyerName;

    @NotBlank(message = "Alıcı telefon numarası giriniz")
    private String buyerPhone;

    @NotBlank(message = "Teslimat adresi alanını doldurunuz")
    private String deliveryAddress;

    @NotBlank(message = "Fatura adresi alanını doldurunuz")
    private String invoiceAddress;

    @Min(value = 0,message = "Total price cannot be negative")
    private BigDecimal orderTotalPrice;

    @OneToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
}
