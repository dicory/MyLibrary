import Books.Book;
import Books.Ebook;
import Books.PrintedBook;
import Books.TypeBook;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Library {


    private static List<Book> books = new ArrayList<>();
    private static List<ReaderBook> readers = new ArrayList<>();

    public static List<Book> getBooks() {
        return books;
    }

    boolean removeBook(Book book) {
        return books.remove(book);
    }

    List<Book> findBookByTitle(String title) {
        ArrayList<Book> list = new ArrayList<>();
        books.stream().filter(book -> book.getTitle().equals(title)).forEach(list::add);
        return list;
    }
    void registerReader(ReaderBook reader) {
        readers.add(reader);
    }
    void unregisterReader(ReaderBook reader) {
        readers.remove(reader);
    }
    List<ReaderBook> findReaderById(String readerId) {
        ArrayList<ReaderBook> list = new ArrayList<>();
        readers.stream().filter(reader -> reader.getReaderId().equals(readerId)).forEach(list::add);
        return list;
    }


    Book borrowBook(String nameBook, TypeBook typeBook) {
        Optional<Book> first = Library.getBooks().stream().filter(search -> search.getTitle().equals(nameBook) && search.getType() == typeBook).findFirst();
        if (first.isPresent()) {
            return first.get();
        } throw new BookNotFoundException("No Book in Library " + nameBook);
    }

    static void delete(){
        books.clear();
    }
    void addBook(Book book) {
        readFile();

        books.add(book);
    }
    static void readFile() {
        books.clear();
        try (BufferedReader br = new BufferedReader(new FileReader
                ("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt")))
        {
            String s;
            while ((s=br.readLine())!=null){
                String[] arr = s.split("\"");//Поправить сплит
                if (arr[9].equals("EBOOK")) {
                    Book ebook = new Ebook(arr[1], arr[3], Integer.parseInt(arr[5]), Double.parseDouble(arr[7]));
                    books.add(ebook);
                }
                if (arr[9].equals("PRINTEDBOOK")) {
                    Book printeBook = new PrintedBook(arr[1], arr[3], Integer.parseInt(arr[5]), Integer.parseInt(arr[7]));
                    books.add(printeBook);

                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
