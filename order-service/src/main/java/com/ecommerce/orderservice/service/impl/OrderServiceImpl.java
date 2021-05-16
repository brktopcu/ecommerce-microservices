package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.domain.BookOrder;
import com.ecommerce.orderservice.domain.Cargo;
import com.ecommerce.orderservice.repository.BookOrderRepository;
import com.ecommerce.orderservice.repository.CargoRepository;
import com.ecommerce.orderservice.request.OrderRequest;
import com.ecommerce.orderservice.response.Book;
import com.ecommerce.orderservice.response.BookSizeResponse;
import com.ecommerce.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final BookOrderRepository bookOrderRepository;
    private final CargoRepository cargoRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderServiceImpl(BookOrderRepository bookOrderRepository,
                            CargoRepository cargoRepository,
                            RestTemplate restTemplate, ObjectMapper objectMapper) {

        this.bookOrderRepository = bookOrderRepository;
        this.cargoRepository = cargoRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String saveOrder(OrderRequest orderRequest, String username, String token) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity cartRequest = new HttpEntity(headers);
        ResponseEntity<BookSizeResponse[]> response = restTemplate.exchange(
                "http://localhost:8082/inventory-service/api/shoppingCart/books",
                HttpMethod.GET,
                cartRequest,
                BookSizeResponse[].class
        );

        System.out.println("Book list response");

        var bookList = Arrays.asList(response.getBody());

        for(BookSizeResponse bookSizeResponse : bookList){
            if(bookSizeResponse.getBook().getBookStock()<=0){
                return "Yeterli stok bulunmamaktadır.";
            }
        }

        for(BookSizeResponse bookSizeResponse : bookList){
            bookSizeResponse.getBook().setBookStock(bookSizeResponse.getBook().getBookStock()-bookSizeResponse.getCount());
            //todo JMS implementation

            HttpEntity<Book> bookRequest = new HttpEntity<>(bookSizeResponse.getBook(), headers);
            restTemplate.postForObject("http://localhost:8082/inventory-service/api/inventory/updateBookStock",
                    bookRequest,
                    String.class);
            System.out.println("Book stock update");
        }

        Cargo cargo = new Cargo();
        cargo.setCargoPrice(orderRequest.getCargoPrice());
        cargoRepository.save(cargo);


        BookOrder order = BookOrder.builder()
                .username(username)
                .buyerName(orderRequest.getName())
                .buyerPhone(orderRequest.getPhone())
                .deliveryAddress(orderRequest.getDeliveryAddress())
                .invoiceAddress(orderRequest.getInvoiceAddress())
                .orderTotalPrice(orderRequest.getTotalPrice())
                .cargo(cargo)
                .build();

        bookOrderRepository.save(order);


        return "Sipariş Oluşturuldu";
    }
}
