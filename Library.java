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


    void addBook(Book book) {
        //readFileTwo(book.getTitle(),TypeBook.EBOOK);
        //books.add(book);
    }

    //метод 2 (как хочу сделать): Не буду по 10 раз перезаписывать файл. Буду проверять в файле, дописывать если нет нужной книги и удалять книгу при необходимости. Всё это нужно скоректировать в методах

    static void readFileTwo(String nameBook, TypeBook typeBook) {
        try (BufferedReader br = new BufferedReader(new FileReader
                ("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt")))
        {
            String s;
            while ((s=br.readLine())!=null){
                String[] arr = s.split("\"");
                if (arr[1].equals(nameBook) && arr[9].equals(String.valueOf(typeBook))){
                    System.out.println("Эта книга уже имеется в библиотеке");
                    return;
                }
            }
            Book printedBookBook = new PrintedBook("2The Lord of the Rings", "J.R.R. Tolkien", 1, 1200);
        try(FileWriter writer = new FileWriter("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt",true))
            {
                writer.write("\n\"" + printedBookBook.getTitle() + "\" \"" + printedBookBook.getAuthor() + "\" \"" + printedBookBook.getIsbn() + "\" \"" + printedBookBook.getAvailable() + "\" \"" + printedBookBook.getType() + "\"\n");
            } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    //это я пока пробую как лучше записывать данные
    static void recordFile(){
        try(FileWriter writer = new FileWriter("C:\\Users\\igor\\Desktop\\MyLibrary\\MyLibrary\\booksFile.txt", false)){
            //нужно реализовать запись
            writer.write(String.valueOf(books.get(1).getTitle()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
