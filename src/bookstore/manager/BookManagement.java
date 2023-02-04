package bookstore.manager;

import bookstore.Status;
import bookstore.model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import utils.Util;

public class BookManagement {

    private static BookManagement instance = new BookManagement();

    public static BookManagement getInstance() {
        return instance;
    }

    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    private BookManagement() {
        this.bookList = new ArrayList();
    }

    public Book addNew() {
        Book book = new Book();
        book.input();

        if (!checkDuplicate(book.getId())) {
            if (this.bookList.add(book)) {
                return book;
            }
        }
        System.out.println("Duplicated.");
        return null;
    }

    public void filterByPublisherId() {
        String publisherId = Util.inputString("Input publisher's ID", false);
        if (publisherId != null && !publisherId.isBlank()) {
            List<Book> resultList = new ArrayList();
            for (Book book : bookList) {
                if (book.getPublisherId().contains(publisherId)) {
                    resultList.add(book);
                }
            }
            if (resultList.isEmpty()) {
                System.out.println("Not found.");
            } else {
                printOutTable(resultList);
            }
        }
    }

    public void filterByBookId() {
        String id = Util.inputString("Input book's ID", false);
        if (id != null && !id.isBlank()) {
            List<Book> resultList = new ArrayList();
            for (Book book : bookList) {
                if (book.getId().contains(id)) {
                    resultList.add(book);
                }
            }
            if (resultList.isEmpty()) {
                System.out.println("Not found.");
            } else {
                printOutTable(resultList);
            }
        }
    }

    public void filterByBookName() {
        String bookName = Util.inputString("Input book's name", false);
        if (bookName != null && !bookName.isBlank()) {
            List<Book> resultList = new ArrayList();
            for (Book book : bookList) {
                if (book.getName().contains(bookName)) {
                    resultList.add(book);
                }
            }
            if (resultList.isEmpty()) {
                System.out.println("Not found.");
            } else {
                printOutTable(resultList);
            }
        }
    }

    public void deleteBook() {
        String id = Util.inputString("Input book's ID", false);
        if (id != null && !id.isBlank()) {
            for (Book book : bookList) {
                if (id.equalsIgnoreCase(book.getId())) {
                    bookList.remove(book);
                    System.out.println("Deleted.");
                    break;
                }
            }
        }
        System.out.println("Not found.");
    }

    public Book getBookById(String id) {
        if (id != null && !id.isBlank()) {
            for (Book book : bookList) {
                if (id.equalsIgnoreCase(book.getId())) {
                    return book;
                }
            }
        }
        return null;
    }

    public void updateBook() {
        String id = Util.inputString("Input book's ID", false);
        Book book = getBookById(id);
        if (book != null) {
            book.input();
            // update() thay vi input
        } else {
            System.out.println("Not found.");
        }
    }

    private boolean checkDuplicate(String Id) {
        for (Book book : bookList) {
            if (book.getId().equalsIgnoreCase(Id)) {
                return true;
            }
        }
        return false;
    }
    
/*
    public void saveToFile() {
        if (bookList.isEmpty()) {
            System.out.println("Nothing to write");
            return;
        }

        try {
            File f = new File("./src/bookstore/data/Book.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Failed to save.");
            return;
        }
        System.out.println("Saved book list.");
    }

    public void loadFromFile() {
        try {
            File f = new File("./src/bookstore/data/Book.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            bookList = (List<Book>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Failed to load book list.");
            return;
        }
        System.out.println("Book List Loaded");
    }
*/
    
    public void saveToFile() {
        if (bookList.isEmpty()) {
            System.out.println("Nothing to write");
            return;
        }

        try {
            File f = new File("./src/bookstore/data/Book.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Book book : bookList) {
                bw.write(book.getId() + "," + book.getName() + "," + book.getPrice() + "," + book.getQuantity() + "," + book.getPublisherId() + "," + book.getStatus());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to save.");
            return;
        }
        System.out.println("Saved book list.");
    }

    public void loadFromFile() {
        try {
            File f = new File("./src/bookstore/data/Book.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            bookList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                Book book = new Book();
                book.setId(parts[0]);
                book.setName(parts[1]);
                book.setPrice(Double.parseDouble(parts[2]));
                book.setQuantity(Integer.parseInt(parts[3]));
                book.setPublisherId(parts[4]);
                book.setStatus(parts[5]);
                
                bookList.add(book);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Failed to load book list.");
            return;
        }
        System.out.println("Book List Loaded");
    }


    public void printOutTable(List<Book> list) {
        Collections.sort(list, new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            int result = Integer.compare(b2.getQuantity(), b1.getQuantity());
            if (result == 0) {
                result = Double.compare(b1.getPrice(), b2.getPrice());
            }
            return result;
        }
    });
        Formatter fmt = new Formatter();
        fmt.format("%8s %15s %15s %15s %15s %15s %15s\n", "Id", "Name", "Price", "Quantity", "Subtotal", "Publisher", "Status");
        for (Book book : list) {
            fmt.format("%8s %15s %15s %15s %15s %15s %15s\n", book.getId(), book.getName(), book.getPrice(), book.getQuantity(), book.getPrice() * book.getQuantity(), book.getPublisherId(), book.getStatus());
        }
        System.out.println(fmt);
    }
}
