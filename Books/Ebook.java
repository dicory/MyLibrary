package Books;

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
        super.borrowBook();
    }

    void returnBook() {
        super.returnBook();
    }
}
