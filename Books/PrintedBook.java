package Books;

public class PrintedBook extends Book {
    int numberOfPages;

    public PrintedBook(String title, String author, int isbn, int numberOfPages) {
        super(title, author, isbn);
        this.numberOfPages = numberOfPages;
    }

    public PrintedBook() {

    }

    @Override
    void borrowBook() {
        setAvailable(false);
    }

    @Override
    void returnBook() {
        setAvailable(true);
    }

}
