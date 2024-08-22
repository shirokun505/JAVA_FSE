public class Library {
    private Book[] books;
    private int size;

    public Library(int capacity) {
        this.books = new Book[capacity];
        this.size = 0;
    }

    // Add a book to the library
    public void addBook(Book book) {
        if (size < books.length) {
            books[size] = book;
            size++;
        } else {
            System.out.println("Library is full.");
        }
    }

    // Linear search by title
    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    // Utility method to sort the array by title (for binary search)
    public void sortBooksByTitle() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (books[i].getTitle().compareToIgnoreCase(books[j].getTitle()) > 0) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
    }

    // Binary search by title (assuming the list is sorted)
    public Book binarySearchByTitle(String title) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = title.compareToIgnoreCase(books[mid].getTitle());

            if (compareResult == 0) {
                return books[mid];
            } else if (compareResult < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    }
}
