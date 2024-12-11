<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mượn sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Form mượn sách</h2>
    <form action="/books?action=saveBorrow" method="post">
        <div class="mb-3">
            <label for="borrowId" class="form-label">Mã mượn sách</label>
            <input type="text" class="form-control" id="borrowId" name="borrowId" required>
        </div>
        <div class="mb-3">
            <label for="bookTitle" class="form-label">Tên sách</label>
            <input type="text" class="form-control" id="bookTitle" name="bookTitle" value="${param.title}" readonly>
        </div>
        <div class="mb-3">
            <label for="studentName" class="form-label">Tên học sinh</label>
            <input type="text" class="form-control" id="studentName" name="studentName" required>
        </div>
        <div class="mb-3">
            <label for="borrowDate" class="form-label">Ngày mượn sách</label>
            <input type="date" class="form-control" id="borrowDate" name="borrowDate" required>
        </div>
        <div class="mb-3">
            <label for="returnDate" class="form-label">Ngày trả sách</label>
            <input type="date" class="form-control" id="returnDate" name="returnDate" required>
        </div>
        <input type="hidden" name="bookId" value="${param.id}">
        <button type="submit" class="btn btn-primary">Xác nhận mượn sách</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>