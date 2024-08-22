public class Main {
    public static void main(String[] args) {
        Library library = new Library(10);

        library.addBook(new Book("B001", "The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book("B002", "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("B003", "1984", "George Orwell"));
        library.addBook(new Book("B004", "Pride and Prejudice", "Jane Austen"));
        library.addBook(new Book("B005", "The Great Gatsby", "F. Scott Fitzgerald"));

        // Linear Search
        System.out.println("Linear Search for '1984':");
        Book foundBook = library.linearSearchByTitle("1984");
        System.out.println(foundBook != null ? foundBook : "Book not found.");

        // Sort the books for Binary Search
        library.sortBooksByTitle();

        // Binary Search
        System.out.println("\nBinary Search for '1984':");
        foundBook = library.binarySearchByTitle("1984");
        System.out.println(foundBook != null ? foundBook : "Book not found.");
    }
}
