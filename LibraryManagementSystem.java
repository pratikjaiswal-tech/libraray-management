import java.util.Scanner;

class Library {
    private String[] bookTitles;
    private String[] authorNames;
    private int[] bookIDs;
    private boolean[] isBookAvailable;
    private int totalBooks;
    private int capacity;

    public Library(int capacity) {
        this.capacity = capacity;
        this.bookTitles = new String[capacity];
        this.authorNames = new String[capacity];
        this.bookIDs = new int[capacity];
        this.isBookAvailable = new boolean[capacity];
        this.totalBooks = 0;
    }

    public void addBook(String title, String author, int id) {
        if (totalBooks < capacity) {
            bookTitles[totalBooks] = title;
            authorNames[totalBooks] = author;
            bookIDs[totalBooks] = id;
            isBookAvailable[totalBooks] = true;
            totalBooks++;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    public void removeBook(int bookID) {
        for (int i = 0; i < totalBooks; i++) {
            if (bookIDs[i] == bookID) {
                // Shift all books after the removed book one position to the left
                for (int j = i; j < totalBooks - 1; j++) {
                    bookTitles[j] = bookTitles[j + 1];
                    authorNames[j] = authorNames[j + 1];
                    bookIDs[j] = bookIDs[j + 1];
                    isBookAvailable[j] = isBookAvailable[j + 1];
                }
                totalBooks--;
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book with this ID not found.");
    }

    public void searchBookByTitle(String title) {
        for (int i = 0; i < totalBooks; i++) {
            if (bookTitles[i].equalsIgnoreCase(title)) {
                System.out.println("Book found: " + bookTitles[i] + " by " + authorNames[i]);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void displayAvailableBooks() {
        System.out.println("Available books in the library:");
        for (int i = 0; i < totalBooks; i++) {
            if (isBookAvailable[i]) {
                System.out.println("ID: " + bookIDs[i] + ", Title: " + bookTitles[i] + ", Author: " + authorNames[i]);
            }
        }
    }

    public void borrowBook(int bookID) {
        for (int i = 0; i < totalBooks; i++) {
            if (bookIDs[i] == bookID && isBookAvailable[i]) {
                isBookAvailable[i] = false;
                System.out.println("You have borrowed the book: " + bookTitles[i]);
                return;
            }
        }
        System.out.println("Book is not available for borrowing.");
    }

    public void returnBook(int bookID) {
        for (int i = 0; i < totalBooks; i++) {
            if (bookIDs[i] == bookID && !isBookAvailable[i]) {
                isBookAvailable[i] = true;
                System.out.println("You have returned the book: " + bookTitles[i]);
                return;
            }
        }
        System.out.println("This book was not borrowed.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library(10);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Display Available Books");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    library.addBook(title, author, id);
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int removeID = scanner.nextInt();
                    library.removeBook(removeID);
                    break;
                case 3:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchBookByTitle(searchTitle);
                    break;
                case 4:
                    library.displayAvailableBooks();
                    break;
                case 5:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowID = scanner.nextInt();
                    library.borrowBook(borrowID);
                    break;
                case 6:
                    System.out.print("Enter book ID to return: ");
                    int returnID = scanner.nextInt();
                    library.returnBook(returnID);
                    break;
                case 7:
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}