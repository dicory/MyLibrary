import Books.Book;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static List<Book> books = new ArrayList<>();

    public Library(){

    }

    public static List<Book> getBooks() {
        return books;
    }

    void addBook(Book book) {
        books.add(book);
    }
    boolean removeBook(Book book) {
        return books.remove(book);
    }
    List<Book> findBookByTitle(String title) {
        ArrayList<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                list.add(book);
            }
        }
        return list;
    }
}
