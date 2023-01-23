package sky;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final String BOOK_NOT_FOUND_REF = BOOK_REFERENCE_PREFIX + "999";
    private static final String THE_GRUFFALO_REFERENCE = BOOK_REFERENCE_PREFIX + "GRUFF472";
    private static final String WINNIE_THE_POOH_REFERENCE = BOOK_REFERENCE_PREFIX + "POOH222";
    private static final String THE_WIND_IN_THE_WILLOWS_REFERENCE = BOOK_REFERENCE_PREFIX + "WILL987";
    private static final String BOOK_WITH_EMPTY_DESCRIPTION_REFERENCE = BOOK_REFERENCE_PREFIX + "EMPTY";
    private static final String BOOK_WITH_NULL_DESCRIPTION_REFERENCE = BOOK_REFERENCE_PREFIX + "NULLDescription";
    private static final String INVALID_REFERENCE = "invalid_ref_code";
    private static final String BOOK_REFERENCE_MUST_START_WITH_BOOK = "Book reference must start with 'BOOK-'";
    private static final String NO_BOOK_FOUND_WITH_THE_REFERENCE = "No book found with the reference ";

    static BookServiceImpl bookService;

    @BeforeAll
    static void setUp() {

        BookRepositoryStub repository = new BookRepositoryStub();
        bookService = new BookServiceImpl(repository);
    }

    @Test
    public void retrieveBookShouldThrowInvalidBookReferenceExceptionIfBookRefIsInvalid1() {

        Exception exception = assertThrows(InvalidBookReferenceFormatException.class, () -> {
            bookService.retrieveBook(INVALID_REFERENCE);
        });
        assertEquals(
                BOOK_REFERENCE_MUST_START_WITH_BOOK,
                exception.getMessage());
    }

    @Test
    public void retrieveBookShouldThrowBookNotFoundExceptionIfNoBookIsFound() {

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.retrieveBook(BOOK_NOT_FOUND_REF);
        });
        assertEquals(
                NO_BOOK_FOUND_WITH_THE_REFERENCE + BOOK_NOT_FOUND_REF,
                exception.getMessage());
    }

    @Test
    public void shouldReturnBookWithMatchingReference() throws BookNotFoundException, InvalidBookReferenceFormatException {

        Book theGraffaloBook = bookService.retrieveBook(THE_GRUFFALO_REFERENCE);
        assertAll("Checking book info",
                () -> assertEquals(theGraffaloBook.getReview(), "A mouse taking a walk in the woods."),
                () -> assertEquals(theGraffaloBook.getTitle(), "The Gruffalo"));
    }

    @Test
    void getBookSummaryShouldThrowInvalidBookReferenceExceptionForInvalidReference() {
        Exception exception = assertThrows(InvalidBookReferenceFormatException.class, () -> {
            bookService.getBookSummary(INVALID_REFERENCE);
        });
        assertEquals(BOOK_REFERENCE_MUST_START_WITH_BOOK, exception.getMessage());
    }

    @Test
    void getBookSummaryShouldThrowBookNotFoundExceptionForBookNotFind() {

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookSummary(BOOK_NOT_FOUND_REF);
        });
        assertEquals(NO_BOOK_FOUND_WITH_THE_REFERENCE + BOOK_NOT_FOUND_REF, exception.getMessage());
    }

    @Test
    void getBookSummaryShouldReturnSummeryOfABook() throws InvalidBookReferenceFormatException, BookNotFoundException {
        String bookSummary = bookService.getBookSummary(THE_GRUFFALO_REFERENCE);
        assertEquals(("[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods."),
                bookSummary);
    }

    @Test
    void getBookSummaryShouldReturnShortSummaryExcludingSpecialCharsInTheLastWord() throws InvalidBookReferenceFormatException, BookNotFoundException {
        String bookSummary = bookService.getBookSummary(THE_WIND_IN_THE_WILLOWS_REFERENCE);
        assertEquals(("[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside..."),
                bookSummary);
    }

    @Test
    void getBookSummaryShouldReturnShortSummaryIfReviewHasMoreThan9words() throws InvalidBookReferenceFormatException, BookNotFoundException {
        String bookSummary = bookService.getBookSummary(WINNIE_THE_POOH_REFERENCE);
        assertEquals(("[BOOK-POOH222] Winnie The Pooh - In this first volume, we meet all the friends..."),
                bookSummary);
    }

    @Test
    void getBookSummaryShouldReturnSummaryForABookWithEmptyDescription() throws InvalidBookReferenceFormatException, BookNotFoundException {
        String bookSummary = bookService.getBookSummary(BOOK_WITH_EMPTY_DESCRIPTION_REFERENCE);
        assertEquals(("[BOOK-EMPTY] Empty Description - "),
                bookSummary);
    }

    @Test
    void getBookSummaryShouldReturnSummaryForABookWithNoDescriptionDefined() throws InvalidBookReferenceFormatException, BookNotFoundException {
        String bookSummary = bookService.getBookSummary(BOOK_WITH_NULL_DESCRIPTION_REFERENCE);
        assertEquals(("[BOOK-NULLDescription] Null Description - "),
                bookSummary);
    }
}

