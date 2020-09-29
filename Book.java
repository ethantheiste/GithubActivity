import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

//--== CS400 File Header Information ==--
//Name: Ethan Theiste
//Email: etheiste@wisc.edu
//Team: CA
//Role: Test Engineer 1
//TA: Yeping Wang
//Lecturer: Gary Dahl
//Notes to Grader: 

public class Book {
  private String title;
  private String author;
  private double rating;
  private long barcode;
  private int numPages;

  // Selects a book from the csv file to return to the user
  // In this method 100 random books can be selected from the data set to the user.
  public static Book newBook(String filePath) {
    Scanner scnr = null;

    Random rand = new Random();
    int randomIndex = rand.nextInt(200);
    Book curBook = new Book();


    try {
      File dataSet = new File(filePath);
      scnr = new Scanner(dataSet);
      String line = "";
      scnr.nextLine();

      for (int i = 0; i < randomIndex - 1; i++) {
        scnr.nextLine();
      }
      line = scnr.nextLine();
      String[] fields = line.split(",");
      curBook.setTitle(fields[1]);
      curBook.setAuthor(fields[2]);
      curBook.setRating(Double.parseDouble(fields[3]));
      curBook.setNumPages(Integer.parseInt(fields[7]));
      curBook.setBarcode(Long.parseLong(fields[5]));

    } catch (NumberFormatException ex) {
      System.out.println(
          "The barcode of the random book has a letter in the barcode, select another book.");
    }
    catch (FileNotFoundException f){
      System.out.println("Error reading from " + filePath);
    }
      finally {
      try {
        scnr.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return curBook;

  }

  // toString Method for an individual book object
  @Override
  public String toString() {
    return title + " by " + author + "\nrating: " + rating + "\nbarcode: " + barcode
        + "\npage count: " + numPages;
  }

  // Constructor for a single book
  public Book() {
    title = null;
    author = null;
    rating = 0.0;
    barcode = 0;
    numPages = 0;
  }

  // Setters
  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public void setBarcode(long barcode) {
    this.barcode = barcode;
  }

  public void setNumPages(int pages) {
    this.numPages = pages;
  }

  // Getters
  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public double getRating() {
    return rating;
  }

  public long getBarcode() {
    return barcode;
  }

  public int getNumPages() {
    return numPages;
  }
}
