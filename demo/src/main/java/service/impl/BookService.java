package service.impl;

import dto.BookBorrowDTO;
import model.Book;
import repository.BookRepository;
import service.IBookService;

import java.util.List;

public class BookService implements IBookService {
    public static final BookRepository bookRepository = new BookRepository();
    @Override
    public List<Book> getAll() {
        return bookRepository.getAll()  ;
    }

    @Override
    public boolean saveBorrow(BookBorrowDTO bookBorrowDTO, String bookId) {
        return bookRepository.saveBorrow(bookBorrowDTO, bookId)  ;
    }


    @Override
    public void update(int id, Object book) {

    }

    @Override
    public List<Book> findByName(String name) {
        return null;
    }
}
