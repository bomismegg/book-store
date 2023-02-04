package bookstore;

import utils.Util;

public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.BOOK,
        MenuItem.PUBLISHER
    };

    private final MenuItem[] bookOptions = {
        MenuItem.BACK,
        MenuItem.BOOK_SHOW,
        MenuItem.BOOK_ADD,
        MenuItem.BOOK_FILTER_BY_NAME,
        MenuItem.BOOK_FILTER_BY_PUBLISHER_ID,
        MenuItem.BOOK_UPDATE,
        MenuItem.BOOK_DELETE,
        MenuItem.BOOK_SAVE_TO_FILE,
        MenuItem.BOOK_LOAD_FROM_FILE
    };

    private final MenuItem[] publisherOptions = {
        MenuItem.BACK,
        MenuItem.PUBLISHER_SHOW,
        MenuItem.PUBLISHER_ADD,
        MenuItem.PUBLISHER_DELETE,
        MenuItem.PUBLISHER_SAVE_TO_FILE,
        MenuItem.PUBLISHER_LOAD_FROM_FILE

    };

    private final MenuItem[] searchOptions = {
        MenuItem.BOOK_FILTER_BY_NAME,
        MenuItem.BOOK_FILTER_BY_PUBLISHER_ID,};

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Book store:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);

        return optionList[choice];
        //        UserController userController = Singleton.getInstance(UserController.class);
        //        for (MenuItem item : optionList) {
        ////            if (userController.checkCurrentUserRole(item.getRole())) {
        //            if (choice == 0) {
        //                return item;
        //            }
        //            choice--;
        ////            }
        //        }
        //        return optionList[0];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
//        UserController userController = Singleton.getInstance(UserController.class);
        for (int i = 1; i < optionList.length; i++) {
//            if (userController.checkCurrentUserRole(optionList[i].getRole())) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
//            }
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;

    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case BOOK ->
                    bookOptions;
                case PUBLISHER ->
                    publisherOptions;
                default ->
                    primaryOptions;
            };
        }

        return optionList;
    }
}
