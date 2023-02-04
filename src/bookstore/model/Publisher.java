package bookstore.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import bookstore.BException;
import utils.Util;

/**
 *
 * @author hasu
 */
public class Publisher implements Serializable {

    public static final String ID_FORMAT = "Pxxxxx";
    private static final String ID_PATTERN = "P\\d{5}";

    private String id;
    private String name;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) throws BException {
        if (!Util.validateStringPattern(id, Publisher.ID_PATTERN, true)) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws BException{
        if (!(phone.length() >= 10 && phone.length() <= 12)) {
                throw new BException("Error");
            }
        this.phone = phone;
    }

    public void input() {
        while (true) {
            try {
                setId(Util.inputString("Input id with patern (" + Publisher.ID_FORMAT + ")", false));
                break;
            } catch (BException ex) {
                Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setName(Util.inputString("Input name", false));
                break;
            } catch (BException ex) {
                
            }
        }

        while (true) {
            try {
                setPhone(Util.inputString("Input phone number", false));
                break;
            } catch (BException ex) {
                
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Publisher{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
}
