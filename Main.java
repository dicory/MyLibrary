import Books.Book;
import Books.Ebook;
import Books.PrintedBook;
import Books.TypeBook;

import java.io.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedIOException, FileNotFoundException {
        //Book ebook = new Ebook("The Lord of the Rings", "J.R.R. Tolkien", 1, 1.2);
        //Book printeBook = new PrintedBook("3112The Lord of the Rings", "J.R.R. Tolkien", 1, 1200);

        Library.addBook("43412The Lord of the Rings", "J.R.R. Tolkien", 1, 1200);
        //library.addBook(ebook);
        //library.addBook(printeBook);
        Library.removeBook("Секреты великого искусства", TypeBook.EBOOK);
        //library.findBookByTitle("The Lord of the Rings");
        //System.out.println(Library.getBooks());
        //System.out.println(library.findBookByTitle("The Lord of the Rings"));


        //ReaderBook player = new ReaderBook("Igor", "18", new ArrayList<Book>());
        //Book theLordOfTheRings = library.borrowBook("The Lord of the Rings", TypeBook.EBOOK);
        //System.out.println(theLordOfTheRings.getAvailable());

        //Library.readFile();
        //Library.getBooks().forEach(book -> System.out.println(book.getTitle()));
        //Library.recordFile();

        //Library.readFileTwo("1Романтика летнего вечера", TypeBook.PRINTEDBOOK);


    }


}