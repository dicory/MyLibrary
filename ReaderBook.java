import Books.Book;

import java.util.List;

public class ReaderBook {
    private String name;
    private String readerId;
    private List<Book> borrowedBooks;

    public ReaderBook(String name, String readerId, List<Book> borrowedBooks) {
        this.name = name;
        this.readerId = readerId;
        this.borrowedBooks = borrowedBooks;
    }


    public ReaderBook(String name, String readerId) {
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
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    void borrowBook(Book book) {

        borrowedBooks.add(book);

    }

    void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}
