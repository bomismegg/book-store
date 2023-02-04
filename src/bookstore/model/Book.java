package bookstore.model;

import bookstore.BException;
import bookstore.Status;
import bookstore.manager.PublisherManagement;
import java.io.Serializable;
import utils.Util;

/**
 *
 * @author hasu
 */
public class Book implements Serializable {

    public static final String ID_FORMAT = "Bxxxxx";
    private static final String ID_PATTERN = "B\\d{5}";

    private String id;
    private String name;
    private double price;
    private int quantity;
    private String publisherId;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) throws BException {
        if (!Util.validateStringPattern(id, Book.ID_PATTERN, true)) {
            throw new BException("Error: id ...");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws BException {
        if (!(name.length() >= 5 && name.length() <= 30)) {
            throw new BException("Error");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws BException {
        if (price <= 0) {
            throw new BException("Error");

        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BException {
        if (quantity <= 0) {
            throw new BException("Error");

        }
        this.quantity = quantity;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) throws BException {
        if (!status.trim().toUpperCase().equals("EXISTS") && !status.trim().toUpperCase().equals("NOT_EXISTS")) {
            throw new BException("Error");
        }
        this.status = Status.valueOf(status.trim().toUpperCase());
    }

    public void input() {

        while (true) {
            try {
                setId(Util.inputString("Input id with patern (" + Book.ID_FORMAT + ")", false));
                break;
            } catch (BException ex) {
                System.out.println("Id has pattern “Bxxxxx”, with xxxxx is five digits");
            }
        }

        while (true) {
            try {
                setName(Util.inputString("Enter name", true));
                break;
            } catch (BException ex) {
                System.out.println("Name has length from 5 to 30 characters");
            }
        }

        while (true) {
            try {
                setPrice(Util.inputDouble("Input price", 0));
                break;
            } catch (BException ex) {
                System.out.println("Price must greater than 0");
            }
        }

        while (true) {
            try {
                setQuantity(Util.inputInteger("Input quantity", 0));
                break;
            } catch (BException ex) {
                System.out.println("Quantity must greater than 0");
            }
        }

        while (true) {
            try {
                setStatus(Util.inputString("Enter status (Exists or Not_Exists)", true));
                break;
            } catch (BException e) {
                System.out.println("Status must be \"Exists\" or \"Not_Exists\"");
            }
        }

        this.publisherId = Util.inputString("Input publisher's id", false);
        if (PublisherManagement.getInstance().getPublisherById(this.publisherId) == null) {
            System.out.println("Publisher not found, add one.");
        }
    }

    public void update() {

        System.out.println("Press enter to skip fields that don't need to be changed");

        String id = Util.inputString("Input id with pattern (" + Book.ID_FORMAT + ")", false);
        if (!id.isEmpty()) {
            while (true) {
                try {
                    setId(id);
                    break;
                } catch (BException ex) {
                    System.out.println("Book’s Id has pattern “Bxxxxx”, with xxxxx is five digits");
                }
            }
        }

        String name = Util.inputString("Enter name", true);
        if (!name.isEmpty()) {
            while (true) {
                try {
                    setName(name);
                    break;
                } catch (BException ex) {
                    System.out.println("Book’s Name has length from 5 to 30 characters");
                }
            }
        }

        String priceString = Util.inputString("Input price", true);
        if (!priceString.isEmpty()) {
            while (true) {
                try {
                    double price = Double.parseDouble(priceString);
                    setPrice(price);
                    break;
                } catch (NumberFormatException | BException ex) {
                    System.out.println("Book’s Price must be a number greater than 0");
                }
            }
        }

        String quantityString = Util.inputString("Input quantity", true);
        if (!quantityString.isEmpty()) {
            while (true) {
                try {
                    int quantity = Integer.parseInt(quantityString);
                    setQuantity(quantity);
                    break;
                } catch (NumberFormatException | BException ex) {
                    System.out.println("Book’s Quantity must be an integer greater than 0");
                }
            }
        }

        String status = Util.inputString("Enter status (Exists or Not_Exists)", true);
        if (!status.isEmpty()) {
            while(true){
                try {
                    setStatus(status);
                    break;
                } catch (BException ex) {
                    System.out.println("Status must be \"Exists\" or \"Not_Exists\"");
                }
            }
        }

        String publisherId = Util.inputString("Input publisher's id", true);
        if (!publisherId.isEmpty()) {
            this.publisherId = publisherId;
            if (PublisherManagement.getInstance().getPublisherById(this.publisherId) == null) {
                System.out.println("Publisher not found, add one.");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append(", publisherId=").append(publisherId);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
