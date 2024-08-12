import Books.Book;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Library {
    String name;
    int readerId;
    List<Book> borrowedBooks;

    public Reader(String name, int readerId, List<Book> borrowedBooks) {
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

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    void borrowBook(Book book){
        for (Book booke : getBooks()) {
            if (book.getTitle().equals(book)) {
                borrowedBooks.add(booke);
                borrowBook(booke);
            }
        }
    }

    void returnBook(Book book){
        for (Book booke : getBooks()) {
            if (book.getTitle().equals(book)) {
                borrowedBooks.remove(booke);
                returnBook(booke);
            }
        }
    }
}
