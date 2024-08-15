import Books.Book;
import Books.Ebook;
import Books.PrintedBook;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Book ebook = new Ebook("The Lord of the Rings", "J.R.R. Tolkien", 1, 1.2);
        Book printeBook = new PrintedBook("2The Lord of the Rings", "J.R.R. Tolkien", 1, 1200);
        Library library = new Library();

        library.addBook(ebook);
        library.addBook(printeBook);

        //library.removeBook(ebook);
        library.findBookByTitle("The Lord of the Rings");
        System.out.println(Library.getBooks());
        System.out.println(library.findBookByTitle("The Lord of the Rings"));


        Reader player = new Reader("Igor", "18", new ArrayList<Book>());
        Book theLordOfTheRings = library.borrrowBook("The Lord of the Rings");
        System.out.println(theLordOfTheRings.getAvailable());
    }


}