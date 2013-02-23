package tutorials.guice.part4.bookstore;

public interface Publisher {

    String getName();

    BookOrderService getOrderingService();

    Iterable<Title> getTitles();

    Iterable<String> getAuthors();
}
