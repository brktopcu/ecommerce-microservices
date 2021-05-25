package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.domain.Category;
import com.ecommerce.inventoryservice.exception.NotFoundException;
import com.ecommerce.inventoryservice.repository.BookRepository;
import com.ecommerce.inventoryservice.request.BookFiltersRequest;
import com.ecommerce.inventoryservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(UUID id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found"));
        return book;
    }


    @Override
    public void saveBookRate(BigDecimal commentRate, UUID bookId) {
        Book book= getBookById(bookId);
        book.setTotalRate(book.getTotalRate().add(commentRate));
        book.setCommentCount(book.getCommentCount().add(BigDecimal.valueOf(1)));
        book.setBookRate(book.getTotalRate().divide(book.getCommentCount(),BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public String deleteBookById(UUID id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found"));

        bookRepository.delete(book);

        return "Book Deleted";
    }

    @Override
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {

        Book book1 = getBookById(book.getBookId());

        book1.setBookPdfDownloadLink(book.getBookPdfDownloadLink());
        book1.setBookBuyLink(book.getBookBuyLink());
        book1.setBookPrice(book.getBookPrice());
        book1.setBookStock(book.getBookStock());

        return bookRepository.save(book1);
    }

    @Override
    public Book setBookOrder(UUID bookId, Integer order) {

        Book book = getBookById(bookId);

        book.setOrderSize(order);

        return bookRepository.save(book);
    }

    @Override
    public Book deleteBookOrder(UUID bookId) {

        Book book = getBookById(bookId);

        book.setOrderSize(0);

        return bookRepository.save(book);
    }

    @Override
    public Boolean addToShoppingCart(UUID bookId) {
        Book book = getBookById(bookId);
        if(book.getOrderSize() == 0) {
            book.setOrderSize(1);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public Book getBookByName(String bookName) {
        return bookRepository.findByBookName(bookName).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public boolean isCategoryInclude(List<Category> categoryList, String filter) {
        boolean isInclude = false;

        if (filter.equals("Kategori") || filter.equals("")) {
            isInclude = true;
        } else {
            for (Category category : categoryList) {
                if (category.getCategoryDescription().equals(filter)) {
                    isInclude = true;
                    break;
                }
            }
        }

        return isInclude;
    }

    public boolean isInPriceRange(Book book, BookFiltersRequest bookFiltersRequest){
        return book.getBookPrice().longValue() <= bookFiltersRequest.getMaxPrice() && book.getBookPrice().longValue() >= bookFiltersRequest.getMinPrice();
    }

    @Override
    public List<Book> getFilteredBooks(BookFiltersRequest bookFiltersRequest) {
        var bookList = getAllBooks();

        if(bookFiltersRequest.getMaxPrice()==null)bookFiltersRequest.setMaxPrice((long) 999.99);
        if(bookFiltersRequest.getMinPrice()==null)bookFiltersRequest.setMinPrice((long) 0.00);


        return bookList.stream()
                .filter(book -> isCategoryInclude(book.getCategoryBooksList(),bookFiltersRequest.getCategory()))
                .filter(book -> isInPriceRange(book,bookFiltersRequest))
                .collect(Collectors.toList());
    }
}
