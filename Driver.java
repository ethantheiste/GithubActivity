import java.util.Scanner;
import java.util.NoSuchElementException;

//--== CS400 File Header Information ==--
//Name: Ethan Theiste
//Email: etheiste@wisc.edu
//Team: CA
//Role: Test Engineer 1
//TA: Yeping Wang
//Lecturer: Gary Dahl
//Notes to Grader: 

/**
 * 
 * Creates a user interface for users to access books based on barcode.
 * 
 * @author Briggs
 *
 */
public class Driver {
  private static long barcode; // Barcode for each book
  private static Book[] lib; // Array of books from csv file
  public static Library checkedOutBooksLib; // Library of checked out books

  public static void main(String[] args) {
    checkedOutBooksLib = new Library(11000);
    lib = new Book[11000];
    for (int i = 0; i < lib.length; i++) {
      lib[i] = Book.newBook("books.csv");
    }

    Library libToSearch = new Library(11000, lib);
    // commands and welcome message
    System.out.println("Welcome to the Library, we hope you enjoy your stay!");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("Commands are as follows: ");
    System.out.println("F: Find a book to make sure it is available");
    System.out.println("T: Take a book out");
    System.out.println("R: Return a book provided it has been checked out");
    System.out.println("Q: Quit");
    System.out.println("Please enter your command: ");

    Scanner sc = new Scanner(System.in);
    String command = "";

    while (!command.equalsIgnoreCase("Q")) {
      command = sc.next();
      // Finding a given book to make sure it is available
      if (command.equalsIgnoreCase("F")) {
        System.out.println("Please input the barcode of the book you wish to find: ");
        barcode = sc.nextLong();
        // If the book does not exist it throws an error
        if (libToSearch.searchISBN(barcode) == null) {
          System.out.println("Book cannot be found.");
        } else {
          // Shows user book they are looking for
          System.out.println("Here is your book: ");
          System.out.println(libToSearch.searchISBN(barcode));
          System.out.println("Please enter your command: ");
        }



        // Taking a book out
      } else if (command.equalsIgnoreCase("T")) {
        System.out.println("Please input the barcode of the book you wish to take out: ");
        barcode = sc.nextLong();
        // If the book does not exist it throws an error
        if (libToSearch.searchISBN(barcode) == null) {
          System.out.println(
              "Book cannot be found; it is either checked out or does not exist in the library");
        } else {
          // Checking out a book removes it from original library,then adds it to library of checked
          // out books by utilizing returnBook(). This is to keep track of checked out books for
          // when the user tries to return a book that has been checked out
          checkedOutBooksLib.returnBook(libToSearch.searchISBN(barcode));
          System.out
              .println("You have successfully checked out " + libToSearch.searchISBN(barcode));
          libToSearch.checkoutBook(barcode);
          System.out.println("Please enter your command: ");
        }



        // Returning a book
      } else if (command.equalsIgnoreCase("R")) {
        System.out.println("Please input the barcode of the book you wish to return: ");
        barcode = sc.nextLong();
        // If the book is still in the original library it throws an error
        if (libToSearch.searchISBN(barcode) != null) {
          System.out.println(
              "Book cannot be returned as it is already in the library.");
        } else {
          // Returns book using returnBook()
          libToSearch.returnBook(checkedOutBooksLib.searchISBN(barcode));
          System.out.println("You have successfully returned " + libToSearch.searchISBN(barcode));
          System.out.println("Please enter your command: ");
        }
      } else {
        System.out.println("Invalid command, please try again.");
      }

    }
  }
}
