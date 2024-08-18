package Books;

public abstract class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean available;

    private TypeBook type;

    public Book(String title, String author, int isbn, TypeBook type) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
        this.type = type;
    }

    public Enum<TypeBook> getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getIsbn() {
        return isbn;
    }
    public boolean getAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    abstract void borrowBook();

    abstract void returnBook();






}
