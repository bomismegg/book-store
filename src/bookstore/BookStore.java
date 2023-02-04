package bookstore;

import bookstore.model.Book;
import bookstore.manager.BookManagement;
import bookstore.manager.PublisherManagement;
import bookstore.model.Publisher;
import utils.Util;

public class BookStore {

    private PublisherManagement publisherManagement;
    private BookManagement bookManagement;

    public BookStore() {
        this.publisherManagement = PublisherManagement.getInstance();
        this.bookManagement = BookManagement.getInstance();
    }

    private void addNewBook() {
        Book book = this.bookManagement.addNew();

        if (book != null
                && this.publisherManagement.getPublisherById(book.getPublisherId()) == null) {
            Publisher pub = this.publisherManagement.addNew();
            book.setPublisherId(pub.getId().toUpperCase());
        }
    }

    private void process() {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        String yn;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case BOOK_SHOW:
                    bookManagement.printOutTable(bookManagement.getBookList());
                    break;
                case BOOK_ADD:
                    do {
                        addNewBook();
                        yn = Util.getYesNo("Continue? (Yes/No):");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case BOOK_FILTER_BY_NAME:
                    do {
                        bookManagement.filterByBookName();
                        yn = Util.getYesNo("Continue? (Yes/No): ");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case BOOK_FILTER_BY_PUBLISHER_ID:
                    do {
                        bookManagement.filterByPublisherId();
                        yn = Util.getYesNo("Continue? (Yes/No): ");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case BOOK_UPDATE:
                    do {
                        bookManagement.updateBook();
                        yn = Util.getYesNo("Continue? (Yes/No): ");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case BOOK_DELETE:
                    do {
                        bookManagement.deleteBook();
                        yn = Util.getYesNo("Continue? (Yes/No): ");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case BOOK_SAVE_TO_FILE:
                    bookManagement.saveToFile();
                    publisherManagement.saveToFile();
                    break;
                case BOOK_LOAD_FROM_FILE:
                    bookManagement.loadFromFile();
                    publisherManagement.loadFromFile();
                    bookManagement.printOutTable(bookManagement.getBookList());
                    break;

                case PUBLISHER_SHOW:
                    publisherManagement.printOutTable();
                    break;
                case PUBLISHER_ADD:
                    do {
                        publisherManagement.addNew();
                        yn = Util.getYesNo("Continue (Yes/No):");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case PUBLISHER_DELETE:
                    do {
                        publisherManagement.deletePublisher();
                        yn = Util.getYesNo("Continue (Yes/No):");
                    } while (yn.equalsIgnoreCase("Yes"));
                    break;
                case PUBLISHER_SAVE_TO_FILE:
                    bookManagement.saveToFile();
                    publisherManagement.saveToFile();
                    break;
                case PUBLISHER_LOAD_FROM_FILE:
                    bookManagement.loadFromFile();
                    publisherManagement.loadFromFile();
                    publisherManagement.printOutTable();
                    break;
                case EXIT:
                    System.out.println("Exited!");
                    break;

                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    public static void main(String[] args) {
        new BookStore().process();
    }

}
