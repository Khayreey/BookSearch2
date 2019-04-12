package khayreey.khayreey.booksearch;

public class dataclass
{
private String book_title,book_author,imageurl,publisher,date,description;

    public dataclass(String book_title, String book_author, String imageurl, String publisher, String date, String description) {
        this.book_title = book_title;
        this.book_author = book_author;
        this.imageurl = imageurl;
        this.publisher = publisher;
        this.date = date;
        this.description = description;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
