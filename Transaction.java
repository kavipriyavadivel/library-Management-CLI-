import java.time.LocalDate;

public class Transaction {
    private static long idCounter = 0;
    private Long TransactionId;
    private LocalDate borrowDate;
    private LocalDate returnDate = null;
    private LocalDate dueDate;
    private Integer fineAmount = 0;
    private Long BookId;

    public Transaction(LocalDate borrowDate, LocalDate dueDate, Long bookId) {
        TransactionId = generateId();
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        BookId = bookId;
    }

    private static synchronized long generateId() {
        return ++idCounter;
    }

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Long transactionId) {
        TransactionId = transactionId;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Integer fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Long getBookId() {
        return BookId;
    }

    public void setBookId(Long bookId) {
        BookId = bookId;
    }

    @Override
    public String toString() {
        return  "TransactionId=" + TransactionId +
                ",\nborrowDate=" + borrowDate +
                ",\nreturnDate=" + returnDate +
                ",\ndueDate=" + dueDate +
                ",\nfineAmount=" + fineAmount +
                ",\nBookId=" + BookId;
    }
}
