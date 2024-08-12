import Books.Book;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static List<Book> books = new ArrayList<>();

    private static List<Reader> readers = new ArrayList<>();

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

    void registerReader(Reader reader) {
        readers.add(reader);
    }

    void unregisterReader(Reader reader) {
        readers.remove(reader);
    }

    List<Reader> findReaderById(String readerId) {
        ArrayList<Reader> list = new ArrayList<>();
        readers.stream().filter(reader -> reader.equals(readerId)).forEach(reader ->list.add(reader));
        return list;
    }
}
