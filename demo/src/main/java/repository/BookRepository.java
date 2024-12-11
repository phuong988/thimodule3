package repository;

import dto.BookBorrowDTO;
import model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement("select b.id, b.title , b.author, b.quantity , b.descriptions\n" +
                    "from books b;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");
                String description = resultSet.getString("descriptions");
                books.add(new Book(id, title, author, quantity, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public boolean saveBorrow(BookBorrowDTO bookBorrowDTO, String bookId) {
        try {
            // Bắt đầu transaction
            BaseRepository.getConnection().setAutoCommit(false);

            // Thêm thông tin mượn sách vào bảng book_borrows
            String insertBorrowSql = "INSERT INTO book_borrows (id, book_id, student_name, borrow_date, return_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement borrowStatement = BaseRepository.getConnection().prepareStatement(insertBorrowSql);
            borrowStatement.setString(1, bookBorrowDTO.getIdBorrow());
            borrowStatement.setString(2, bookId);
            borrowStatement.setString(3, bookBorrowDTO.getNameStudent());
            borrowStatement.setDate(4, java.sql.Date.valueOf(bookBorrowDTO.getBorrowDate()));
            borrowStatement.setDate(5, java.sql.Date.valueOf(bookBorrowDTO.getReturnDate()));
            borrowStatement.executeUpdate();

            // Cập nhật số lượng sách trong bảng books
            String updateBookSql = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
            PreparedStatement bookStatement = BaseRepository.getConnection().prepareStatement(updateBookSql);
            bookStatement.setString(1, bookId);
            bookStatement.executeUpdate();

            // Commit transaction
            BaseRepository.getConnection().commit();
            return true;
        } catch (SQLException e) {
            try {
                // Rollback nếu có lỗi
                BaseRepository.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                BaseRepository.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

