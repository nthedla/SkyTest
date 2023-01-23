package sky;

public interface BookService {
    Book retrieveBook(String bookReference) throws BookNotFoundException, InvalidBookReferenceFormatException;
    String getBookSummary(String bookReference) throws BookNotFoundException, InvalidBookReferenceFormatException;
}
