package Books;

import javax.naming.OperationNotSupportedException;

public class Ebook extends Book {
    double fileSize;

    public Ebook(String title, String author, int isbn, double fileSize) {
        super(title, author, isbn);
        this.fileSize = fileSize;
    }

    public Ebook() {

    }

    @Override
    void borrowBook() {
        System.out.println("Ebooks cannot be borrowed");
    }

    void returnBook() {
        System.out.println("Ebooks cannot be returned");
    }
}
