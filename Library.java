import Books.Book;
import Books.Ebook;
import Books.PrintedBook;
import Books.TypeBook;
import com.sun.jdi.Value;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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


    static void registerReader(ReaderBook reader) {
        try (FileWriter writer = new FileWriter("readersFile.txt",true)){
            writer.write("\"" + reader.getName() + "\" \"" + reader.getReaderId() + "\" \"" + reader.getBorrowedBooks() + "\"\n");
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        readers.add(reader);
    }
    static void unregisterReader(String reader) {
        List<String> listReader = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("readersFile.txt"))){
            String s;
            while ((s = br.readLine()) != null){
                String[] arr = s.split("\"");
                if (!arr[1].equals(reader))
                {
                    listReader.add(s);
                }
                System.out.println(arr[1]);
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter("readersFile.txt",false)){
            for (String readerPlayer:listReader) {
                writer.write(readerPlayer + "\n");
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
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


    void uploadBooks(String path){
        try (BufferedReader br = new BufferedReader(new FileReader
                (path)))
        {
            String s;
            while ((s = br.readLine())!=null){
                String[] arr = s.split(";");
                if (volidateParam(arr)){
                    Book oneBook = new Ebook(arr[1],arr[2], Integer.parseInt(arr[3]),Double.parseDouble(arr[4]));
                    books.add(oneBook);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void uploadReaders(String path){
        try (BufferedReader br = new BufferedReader(new FileReader
                (path)))
        {
            String s;
            while ((s = br.readLine())!=null){
                String[] arr = s.split(";");
                if(volidateParam(arr)){
                    ReaderBook readerPlayer = new ReaderBook(arr[0],arr[1]);
                    readers.add(readerPlayer);
                } else {
                    throw new InvalidReaderException("Вычетанный пользователь невалиден");
                }



            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    boolean volidateParam(String[] arr){
        for (String s:arr) {
            if(s != null && !s.isBlank()){
                continue;
            } else {
                throw new InvalidReaderException("Переданный массив невалиден");
            }
        }
        return true;
    }

    Map<TypeBook, List<Book>> groupBooksByType(){

    }

    Map<String, List<Book>> groupBooksByName(){

    }
}


