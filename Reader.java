import Books.Book;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Library {
    private String name;
    private String readerId;
    private static List<Book> borrowedBooks = new ArrayList<>();

    public Reader(String name, String readerId, List<Book> borrowedBooks) {
        this.name = name;
        this.readerId = readerId;
        this.borrowedBooks = borrowedBooks;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReaderId() {
        return readerId;
    }
    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }
    public static List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    void borrowBook(Book book){
        getBooks().stream().filter(newBook -> newBook.equals(book)).forEach(newBook -> {borrowedBooks.add(newBook);borrowBook(newBook);});
    }
    void returnBook(Book book){
        getBooks().stream().filter(killBook -> killBook.equals(book)).forEach(killBook -> {borrowedBooks.remove(killBook);returnBook(killBook);});
    }
}
