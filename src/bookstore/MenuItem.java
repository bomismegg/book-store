package bookstore;

public enum MenuItem {
    BACK("Back"),
    EXIT("Exit"),
    BOOK("Book"),
    BOOK_SHOW("Show the list"),
    BOOK_ADD("Create new book"),
    BOOK_UPDATE("Update"),
    BOOK_DELETE("Delete"),
    BOOK_FILTER_BY_ID("Filter by id"),
    BOOK_FILTER_BY_NAME("Filter by name"),
    BOOK_FILTER_BY_PUBLISHER_ID("Filter by publisher's id"),
    BOOK_SAVE_TO_FILE("Save data to file"),
    BOOK_LOAD_FROM_FILE("Print out book list from file"),
    //...

    PUBLISHER("Publisher"),
    PUBLISHER_SHOW("Show the list"),
    PUBLISHER_ADD("Create new publisher"),
    PUBLISHER_UPDATE("Update"),
    PUBLISHER_DELETE("Delete"),
    PUBLISHER_FILTER_BY_ID("Filter by id"),
    PUBLISHER_SAVE_TO_FILE("Save data to file"),
    PUBLISHER_LOAD_FROM_FILE("Print out publisher list from file")
    //...
    ;

    private final String label;

    public String getLabel() {
        return label;
    }

    private MenuItem(String label) {
        this.label = label;
    }

}
