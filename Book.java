public class Book {
    private static long idCounter = 0;
    private Long Bookid;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable = true;

    public Book(String title, String author, String genre) {
        Bookid = generateId();
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    private static synchronized long generateId() {
        return ++idCounter;
    }

    public Long getBookid() {
        return Bookid;
    }

    public void setBookid(Long bookid) {
        Bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return  "Bookid=" + Bookid +
                ",\ntitle='" + title + '\'' +
                ",\nauthor='" + author + '\'' +
                ",\ngenre='" + genre + '\'' +
                ",\nisAvailable=" + isAvailable;
    }
}
