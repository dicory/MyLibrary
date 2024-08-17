package Books;



public class Ebook extends Book {
    double fileSize;



    public Ebook(String title, String author, int isbn, double fileSize) {
        super(title, author, isbn, TypeBook.EBOOK);
        this.fileSize = fileSize;
    }


    @Override
    void borrowBook() {
        System.out.println("Ebooks cannot be borrowed");
    }

    void returnBook() {
        System.out.println("Ebooks cannot be returned");
    }
}
