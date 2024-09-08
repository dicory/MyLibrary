import Books.Book;
import Books.Ebook;
import Books.PrintedBook;
import Books.TypeBook;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {


    private static List<Book> books = new ArrayList<>();
    private static List<ReaderBook> readers = new ArrayList<>();

    public static List<Book> getBooks() {
        return books;
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
        }
        throw new BookNotFoundException("No Book in Library " + nameBook);
    }

    void addBook(Book book) {
        books.add(book);
    }

    boolean removeBook(Book book) {
        return books.remove(book);
    }

    static void readRecordFile(String name, String author, String id, String bookSize, String Type) {
        try (BufferedReader br = new BufferedReader(new FileReader
                ("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt"))) {
            String s;
            while ((s = br.readLine()) != null) {
                String[] arr = s.split("\"");
                System.out.println(arr[1]);
                if (arr[1].equals(name) && arr[9].equals(Type)) {
                    System.out.println("Эта книга уже имеется в библиотеке");
                    return;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt", true)) {
            writer.write("\n\"" + name + "\" \"" + author + "\" \"" + id + "\" \"" + bookSize + "\" \"" + Type + "\"");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    void uploadBooks(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader
                (path))) {
            String s;
            while ((s = br.readLine()) != null) {
                String[] arr = s.split(";");
                if (volidateParam(arr)) {
                    Book oneBook = new Ebook(arr[1], arr[2], Integer.parseInt(arr[3]), Double.parseDouble(arr[4]));
                    books.add(oneBook);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void uploadReaders(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader
                (path))) {
            String s;
            while ((s = br.readLine()) != null) {
                String[] arr = s.split(";");
                if (volidateParam(arr)) {
                    booksParser(arr[2]);
                    ReaderBook readerPlayer = new ReaderBook(arr[0], arr[1]);
                    readers.add(readerPlayer);
                } else {
                    throw new InvalidReaderException("Вычетанный пользователь невалиден");
                }


            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    boolean volidateParam(String[] arr) {
        for (String s : arr) {
            if (s != null && !s.isBlank()) {
                continue;
            } else {
                throw new InvalidReaderException("Переданный массив невалиден");
            }
        }
        return true;
    }


    //Групированный список моих кних по их типу

    //Cписок книг используя стримы или foreach
    Map<TypeBook, List<Book>> groupBooksByType() {
        uploadBooks("booksFile");
        return books.stream().collect(Collectors.groupingBy(Book::getType));
    }

    //Групировать книги по названиям
    Map<String, List<Book>> groupBooksByName() {
        return books.stream().collect(Collectors.groupingBy(Book::getTitle));
    }

    List<Book> booksParser(String books) {
        ArrayList<Book> booksResults = new ArrayList<>();
        String[] arrReaderBooks = books.split(",");
        for (String book : arrReaderBooks) {
            book = book.replace("[","");
            book = book.replace("]","");
            Optional<Book> first = findBookByTitle(book).stream().filter(bookOne -> bookOne.getAvailable()).findFirst();
            first.ifPresent(bookResults -> {
                if (bookResults.getType().equals(TypeBook.PRINTEDBOOK))
                {
                    bookResults.setAvailable(false);
                }
                booksResults.add(bookResults);
            });
        }
        booksResults.forEach(resultBook-> System.out.println(resultBook.getTitle()));
        return booksResults;
    }

}


