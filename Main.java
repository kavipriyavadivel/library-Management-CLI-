import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);
    List<Book> Books = new ArrayList<>();
    List<Transaction> Transactions = new ArrayList<>();

    public void initializeBooks() {
        Books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        Books.add(new Book("1984", "George Orwell", "Dystopian"));
        Books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic"));
        Books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction"));
        Books.add(new Book("Moby Dick", "Herman Melville", "Adventure"));
        Books.add(new Book("Harry Potter and the Sorcerer’s Stone", "J.K. Rowling", "Fantasy"));
        Books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy"));
        Books.add(new Book("The Alchemist", "Paulo Coelho", "Philosophical Fiction"));
        Books.add(new Book("Pride and Prejudice", "Jane Austen", "Romance"));
        Books.add(new Book("The Lean Startup", "Eric Ries", "Business"));
    }

    public void AddBook() {
        System.out.println("Enter the Book name: ");
        String name = scan.nextLine(); // Read full line for book name

        System.out.println("Enter the Book author: ");
        String author = scan.nextLine(); // Read full line for author

        System.out.println("Enter the Book genre: ");
        String genre = scan.nextLine(); // Read full line for genre

        Book newBook = new Book(name, author, genre);
        Books.add(newBook);
        System.out.println("Book Added Successfully...");
        System.out.println();
    }

    public void DeleteBook(Long BookId){
        Iterator<Book> iterator = Books.iterator();
        while(iterator.hasNext()){
            Book book = iterator.next();
            if(book.getBookid().equals(BookId)){
                iterator.remove();
                System.out.println("Book is removed from Library");
                System.out.println();
                return;
            }
        }
    }

    public void BorrowBook(Long BookId){
        Iterator<Book> iterator = Books.iterator();
        while(iterator.hasNext()){
            Book book = iterator.next();
            if(book.getBookid().equals(BookId)){
                if(book.isAvailable()) {
                    book.setAvailable(false);
                    Transaction newTransaction = new Transaction(LocalDate.now(), LocalDate.now().plusDays(14), BookId);
                    Transactions.add(newTransaction);
                    System.out.println("You lend the Book : " + book.getTitle() + "!");
                    System.out.println();
                    return;
                }
                else{
                    System.out.println("Book is out of Stock");
                    System.out.println();
                    return;
                }
            }
        }
    }

    public void ReturnBook(Long BookId){
        Iterator<Book> iterator = Books.iterator();
        Iterator<Transaction> iter = Transactions.iterator();
        while(iter.hasNext()){
            Transaction trans = iter.next();
            if(trans.getBookId().equals(BookId) && trans.getReturnDate() == null){
                trans.setReturnDate(LocalDate.now());
                if (trans.getReturnDate().isAfter(trans.getDueDate())){
                    long overDueDays = (trans.getReturnDate().toEpochDay() - trans.getDueDate().toEpochDay());
                    trans.setFineAmount((int) (overDueDays * 2));
                    System.out.println("Your Fine Amount is : "+ trans.getFineAmount());
                    System.out.println();
                }
                break;
            }
        }
        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getBookid().equals(BookId)){
                System.out.println("Thank You. Keep Reading!!!");
                System.out.println();
                book.setAvailable(true);
                return;
            }
        }
    }

    public void TransactionDetails(Long BookId){
        Iterator<Transaction> iterator = Transactions.iterator();
        int flag = 0;
        while(iterator.hasNext()){
            Transaction trans = iterator.next();
            if(trans.getBookId().equals(BookId)){
                System.out.println(trans);
                System.out.println();
                flag = 1;
            }
        }
        if(flag == 0)
            System.out.println("No Transaction For the Book");
        System.out.println();
    }

    public void ViewBooks() {
        if (Books.isEmpty()) {
            System.out.println("No books are available in the library.");
            System.out.println();
            return;
        }

        System.out.println("Available Books in the Library:");
        for (Book book : Books) {
            System.out.println(book);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main library = new Main();
        library.initializeBooks();
        System.out.println("Welcome to Library Management Application");
        while(true) {
            System.out.println("How can we help you?");
            System.out.println("1.Add Books to the Library");
            System.out.println("2.View Books from the library");
            System.out.println("3.Delete Book from the library");
            System.out.println("4.Borrow Book from the library");
            System.out.println("5.Return Book to the library");
            System.out.println("6.See Transaction Details");
            System.out.println("7.Exit");
            System.out.print("Choose any one (1, 2, 3, 4, 5, 6, 7): ");
            int choice = library.scan.nextInt();
            library.scan.nextLine();
            switch (choice) {
                case 1:
                    library.AddBook();
                    break;
                case 2:
                    library.ViewBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID to delete: ");
                    long deleteId = library.scan.nextLong();
                    library.DeleteBook(deleteId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to borrow: ");
                    long borrowId = library.scan.nextLong();
                    library.BorrowBook(borrowId);
                    break;
                case 5:
                    System.out.print("Enter Book ID to return: ");
                    long returnId = library.scan.nextLong();
                    library.ReturnBook(returnId);
                    break;
                case 6:
                    System.out.print("Enter Book ID to see transactions: ");
                    long Id = library.scan.nextLong();
                    library.TransactionDetails(Id);
                    break;
                case 7:
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    library.scan.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
            System.out.println();
        }
    }
}