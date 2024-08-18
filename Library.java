import Books.Book;
import Books.Ebook;
import Books.PrintedBook;
import Books.TypeBook;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        books.stream().filter(book -> book.getTitle().equals(title)).forEach(list::add);
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
        readers.stream().filter(reader -> reader.getReaderId().equals(readerId)).forEach(list::add);
        return list;
    }


    Book borrowBook(String nameBook, TypeBook typeBook) {
        Optional<Book> first = Library.getBooks().stream().filter(search -> search.getTitle().equals(nameBook) && search instanceof Ebook && search.getType() == typeBook).findFirst();
        if (first.isPresent()) {
            return first.get();
        } throw new BookNotFoundException("No Book in Library " + nameBook);
    }

}
