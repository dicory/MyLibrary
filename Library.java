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


    public static void addBook(String name, String author, int id, double fileSize) {
        readRecordFile(name, author, String.valueOf(id), String.valueOf(fileSize), "EBOOK");
    }
    public static void addBook(String name, String author, int id, int numberOfPages) {
        readRecordFile(name, author, String.valueOf(id), String.valueOf(numberOfPages), "PRINTEDBOOK");
    }

   static void removeBook(String name, TypeBook type) {
       List<String> priceBook = new ArrayList<>();
       try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt"))) {
           String s;
           while ((s = br.readLine()) != null) {
               String[] arr = s.split("\"");
               System.out.println(arr[1]);
               if (!(arr[1].equals(name) && arr[9].equals(String.valueOf(type)))) {
                   priceBook.add(s);
               }
           }
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }

       try(FileWriter writer = new FileWriter("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt",false))
       {
           for (String book:priceBook) {
               writer.write(book + "\n");
           }
       } catch (IOException ex){
           System.out.println(ex.getMessage());
       }
    }

    static void readRecordFile(String name, String author, String id, String bookSize, String Type) {
        try (BufferedReader br = new BufferedReader(new FileReader
                ("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt")))
        {
            String s;
            while ((s = br.readLine())!=null){
                String[] arr = s.split("\"");
                System.out.println(arr[1]);
                if (arr[1].equals(name) && arr[9].equals(Type)){
                    System.out.println("Эта книга уже имеется в библиотеке");
                    return;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt",true))
        {
            writer.write("\n\"" + name + "\" \"" + author + "\" \"" + id + "\" \"" + bookSize + "\" \"" + Type + "\"");
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
