package sky;

import java.util.Arrays;
import java.util.Objects;

public class BookServiceImpl implements BookService {

    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final String BOOK_REFERENCE_MUST_START_WITH_BOOK = "Book reference must start with 'BOOK-'";
    private static final String NO_BOOK_FOUND_WITH_THE_REFERENCE = "No book found with the reference ";
    private BookRepository bookRepository;

    protected BookServiceImpl(BookRepository repository) {
        this.bookRepository = repository;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException, InvalidBookReferenceFormatException {
        if (!bookReference.startsWith(BOOK_REFERENCE_PREFIX)) {
            throw new InvalidBookReferenceFormatException(BOOK_REFERENCE_MUST_START_WITH_BOOK);
        }
        Book book = bookRepository.retrieveBook(bookReference);

        if (Objects.isNull(book)) {
            throw new BookNotFoundException(NO_BOOK_FOUND_WITH_THE_REFERENCE + bookReference);
        }
        return book;
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException, InvalidBookReferenceFormatException {
        String summary = "";

        Book book = retrieveBook(bookReference);

        String bookReview = book.getReview();
        String partOfSummary = summary.concat("[" + bookReference + "] ").concat(book.getTitle() + " - ");

        String[] wordsInReview = new String[0];
        if (!Objects.isNull(bookReview))
            wordsInReview = bookReview.split(" ");

        if (wordsInReview.length > 9) {
            String review = String.join(" ", Arrays.asList(wordsInReview).subList(0, 9));
            return partOfSummary.concat(review).replaceAll("\\W$", "").concat("...");
        }
        return Objects.isNull(bookReview) ? partOfSummary : partOfSummary.concat(bookReview);
    }
}
