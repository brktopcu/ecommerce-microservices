package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.domain.Cargo;
import com.ecommerce.inventoryservice.domain.ShoppingCart;
import com.ecommerce.inventoryservice.repository.ShoppingCartRepository;
import com.ecommerce.inventoryservice.response.BookSizeResponse;
import com.ecommerce.inventoryservice.response.TotalPriceResponse;
import com.ecommerce.inventoryservice.service.BookService;
import com.ecommerce.inventoryservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final BookService bookService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(BookService bookService, ShoppingCartRepository shoppingCartRepository) {
        this.bookService = bookService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart addBookToCard(UUID bookID, String username) {

        Book book = bookService.getBookById(bookID);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUsername(username);
        if(shoppingCart!=null){
            shoppingCart.getShoppingCartBooks().add(book);
            shoppingCartRepository.save(shoppingCart);
            return shoppingCart;
        }

        ShoppingCart newCart = ShoppingCart.builder()
                .shoppingCartBooks(Arrays.asList(book))
                .username(username)
                .build();

        shoppingCartRepository.save(newCart);

        return newCart;

    }

    @Override
    public List<BookSizeResponse> userShoppingCart(String username) {

        var shoppingCart = shoppingCartRepository.findByUsername(username);

        var shoppingCartBooks = shoppingCart.getShoppingCartBooks();

        var bookListMap = shoppingCartBooks.stream()
                .collect(groupingBy(Book::getBookId));


        var bookSizeResponseList = new ArrayList<BookSizeResponse>();

        bookListMap.keySet()
                .forEach(key -> {
                    var response = new BookSizeResponse();
                    response.setBook(bookService.getBookById(key));
                    response.setCount(bookListMap.get(key).size());
                    bookSizeResponseList.add(response);
                });
        return bookSizeResponseList;

    }


    @Override
    public TotalPriceResponse getTotalPrice(String username) {

        var shoppingCart = shoppingCartRepository.findByUsername(username);

        var shoppingCartBooks = shoppingCart.getShoppingCartBooks();

        BigDecimal price = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        var cargo = new Cargo();

        for(Book book : shoppingCartBooks){
            price = price.add(book.getBookPrice());
        }

        totalPrice = price.add(cargo.getCargoPrice());

        var response = new TotalPriceResponse();
        response.setPrice(price);
        response.setTotalPrice(totalPrice);
        response.setCargo(cargo);

        return response;
    }

    @Override
    public List<Book> decreaseBookOrderFromCard(UUID bookId, String username) {

        Book book = bookService.getBookById(bookId);
        var shoppingCart = shoppingCartRepository.findByUsername(username);
        shoppingCart.getShoppingCartBooks().remove(book);
        shoppingCartRepository.save(shoppingCart);

        return shoppingCart.getShoppingCartBooks();
    }

    @Override
    public void create(String username) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByUsername(username);

        if(shoppingCart == null){
            ShoppingCart cart = ShoppingCart.builder()
                    .username(username)
                    .shoppingCartBooks(new ArrayList<>())
                    .build();

            shoppingCartRepository.save(cart);
        }

    }

    @Override
    public List<Book> removeBookFromCart(String username, UUID bookId) {

        var userShoppingCart = shoppingCartRepository.findByUsername(username);
        var bookOrders = userShoppingCart.getShoppingCartBooks();
        var bookList = bookOrders.stream()
                .filter(book -> !book.getBookId().equals(bookId) )
                .collect(Collectors.toList());

        userShoppingCart.setShoppingCartBooks(bookList);
        shoppingCartRepository.save(userShoppingCart);

        return userShoppingCart.getShoppingCartBooks();
    }

    @Override
    public List<Book> getSoppingCartBooksByUsername(String username) {
        var userShoppingCart = shoppingCartRepository.findByUsername(username);

        return userShoppingCart.getShoppingCartBooks();
    }

}
