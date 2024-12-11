package controller;

import dto.BookBorrowDTO;
import model.Book;
import service.IBookService;
import service.impl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns = "/books"  )
public class BookController extends HttpServlet {
    private static IBookService iBookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "borrow":
                String bookId = req.getParameter("id");
                String bookTitle = req.getParameter("title");
                req.setAttribute("bookId", bookId);
                req.setAttribute("bookTitle", bookTitle);
                req.getRequestDispatcher("/view/borrow.jsp").forward(req, resp);
                break;
            default:
                List<Book> books = iBookService.getAll();
                req.setAttribute("books", books);
                req.getRequestDispatcher("/view/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "saveBorrow":
                String borrowId = req.getParameter("borrowId");
                String bookTitle = req.getParameter("bookTitle");
                String studentName = req.getParameter("studentName");
                LocalDate borrowDate = LocalDate.parse(req.getParameter("borrowDate"));
                LocalDate returnDate = LocalDate.parse(req.getParameter("returnDate"));
                String bookIdToBorrow = req.getParameter("bookId");

                BookBorrowDTO bookBorrowDTO = new BookBorrowDTO(borrowId, bookTitle, studentName, borrowDate, returnDate);

                // Gọi service để lưu thông tin mượn sách
                boolean success = iBookService.saveBorrow(bookBorrowDTO, bookIdToBorrow);

                if (success) {
                    req.setAttribute("message", "Mượn sách thành công!");
                } else {
                    req.setAttribute("message", "Có lỗi xảy ra khi mượn sách!");
                }

                // Chuyển hướng về trang danh sách sách
                resp.sendRedirect("/books");
                break;
            case "search":
                String name = req.getParameter("name");
                List<Book> bookList = iBookService.findByName(name);
                req.setAttribute("book", bookList);
                req.getRequestDispatcher("viewsearch.jsp").forward(req, resp);
                break;
        }

    }
}