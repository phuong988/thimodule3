package service;

import dto.BookBorrowDTO;

import java.util.List;

public interface IService<T>{
    List<T> getAll();
    boolean saveBorrow(BookBorrowDTO bookBorrowDTO, String bookId);
    List<T> findByName(String name);
}
