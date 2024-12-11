package dto;

import java.time.LocalDate;

public class BookBorrowDTO {
    private String idBorrow;
    private String bookTitle;
    private String nameStudent;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BookBorrowDTO(String idBorrow, String bookTitle, String nameStudent, LocalDate borrowDate, LocalDate returnDate) {
        this.idBorrow = idBorrow;
        this.bookTitle = bookTitle;
        this.nameStudent = nameStudent;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getIdBorrow() {
        return idBorrow;
    }

    public void setIdBorrow(String idBorrow) {
        this.idBorrow = idBorrow;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String title) {
        this.bookTitle = title;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
