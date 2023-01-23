 # BooKService API
> This is an implementation of a `book service` API to retrieve books data by `referenceNumber`. And this API will validate the `referenceNumber` before retrieve the book result from the `stubRepository`. If book reference is not valid then returns custom exception(InvalidBookReferenceFormatException). If the book is not find in the `stubRepository` then it returns custom exception(BookNotFoundException).

## Run API on local
- First of all run  `./gradlew install`. _(**Note:** Only first time to run this)_
 
 ### Expected output in console
```shell
BUILD SUCCESSFUL in 791ms
4 actionable tasks: 1 executed, 3 up-to-date
```
- Run the command to build the app `./gradlew build`

### Expected Output
```shell
BUILD SUCCESSFUL in 733ms
7 actionable tasks: 7 up-to-date
```

- Run a specific test or `BookServiceImplTest` only  `./gradlew test --tests BookServiceImplTest`
### Expected Output 
```shell
> Task :app:test

BookServiceImplTest > getBookSummaryShouldThrowInvalidBookReferenceExceptionForInvalidReference() PASSED

BookServiceImplTest > getBookSummaryShouldThrowBookNotFoundExceptionForBookNotFind() PASSED

BookServiceImplTest > retrieveBookShouldThrowBookNotFoundExceptionIfNoBookIsFound() PASSED

BookServiceImplTest > retrieveBookShouldThrowInvalidBookReferenceExceptionIfBookRefIsInvalid1() PASSED

BookServiceImplTest > getBookSummaryShouldReturnSummaryForABookWithNoDescriptionDefined() PASSED

BookServiceImplTest > getBookSummaryShouldReturnSummaryForABookWithEmptyDescription() PASSED

BookServiceImplTest > getBookSummaryShouldReturnSummeryOfABook() PASSED

BookServiceImplTest > getBookSummaryShouldReturnShortSummaryIfReviewHasMoreThan9words() PASSED

BookServiceImplTest > shouldReturnBookWithMatchingReference() PASSED

BookServiceImplTest > getBookSummaryShouldReturnShortSummaryExcludingSpecialCharsInTheLastWord() PASSED

BUILD SUCCESSFUL in 1s
3 actionable tasks: 1 executed, 2 up-to-date
```

_**(Note:)**_ You need the below configurations to show test results on console for each test method.
```yaml
test {
    testLogging {
        events "passed", "skipped", "failed", "standardOut", "standardError"
    }
}
```
