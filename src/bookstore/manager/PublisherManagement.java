package bookstore.manager;

import bookstore.model.Publisher;
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

public class PublisherManagement {

    private static PublisherManagement instance = new PublisherManagement();

    public static PublisherManagement getInstance() {
        return instance;
    }

    private List<Publisher> publisherList;

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    private PublisherManagement() {
        this.publisherList = new ArrayList();
    }

    public Publisher addNew() {
        Publisher pub = new Publisher();
        pub.input();
        if (!checkDuplicate(pub.getId())) {
            if (this.publisherList.add(pub)) {
                return pub;
            }
        }
        System.out.println("Duplicated.");
        return null;
    }

    public void deletePublisher() {
        String id = Util.inputString("Input publisher's ID", false);
        if (id != null && !id.isBlank()) {
            for (Publisher pub : publisherList) {
                if (id.equalsIgnoreCase(pub.getId())) {
                    publisherList.remove(pub);
                    System.out.println("Deleted.");
                    break;
                }
            }
        }
        System.out.println("Not found.");
    }

    public Publisher getPublisherById(String publisherId) {
        if (publisherId != null && !publisherId.isBlank()) {
            for (Publisher pub : publisherList) {
                if (publisherId.equalsIgnoreCase(pub.getId())) {
                    return pub;
                }
            }
        }
        return null;
    }

    public List<Publisher> filterById(String publisherId) {
        if (publisherId != null && !publisherId.isBlank()) {
            this.publisherList.stream().filter(pub -> publisherId.equalsIgnoreCase(pub.getId())).sorted().toList();
        }
        return null;
    }

    private boolean checkDuplicate(String Id) {
        for (Publisher pub : publisherList) {
            if (pub.getId().equals(Id)) {
                return true;
            }
        }
        return false;
    }

    /*
    public void saveToFile() {
        if (publisherList.isEmpty()) {
            System.out.println("Nothing to write");
            return;
        }

        try {
            File f = new File("./src/bookstore/data/Publisher.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(publisherList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Failed to save.");
            return;
        }
        System.out.println("Saved publisher list.");
    }

    public void loadFromFile() {
        try {
            File f = new File("./src/bookstore/data/Publisher.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            publisherList = (List<Publisher>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Failed to load publisher list.");
            return;
        }
        System.out.println("Publisher List Loaded.");
    }
     */
    public void saveToFile() {
        if (publisherList.isEmpty()) {
            System.out.println("Nothing to write");
            return;
        }

        try {
            File f = new File("./src/bookstore/data/Publisher.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Publisher pub : publisherList) {
                String line = String.format("%s,%s,%s\n", pub.getId(), pub.getName(), pub.getPhone());
                bw.write(line);
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to save.");
            return;
        }
        System.out.println("Saved publisher list.");
    }

    public void loadFromFile() {
        try {
            File f = new File("./src/bookstore/data/Publisher.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            publisherList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Publisher pub = new Publisher();
                pub.setId(parts[0]);
                pub.setName(parts[1]);
                pub.setPhone(parts[2]);

                publisherList.add(pub);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Failed to load publisher list.");
            return;
        }
        System.out.println("Publisher List Loaded.");
    }

    public void printOutTable() {
        Collections.sort(publisherList, new Comparator<Publisher>() {
            @Override
            public int compare(Publisher p1, Publisher p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        Formatter fmt = new Formatter();
        fmt.format("%8s %15s %15s\n", "Id", "Name", "Phone");
        for (Publisher pub : publisherList) {
            fmt.format("%8s %15s %15s\n", pub.getId(), pub.getName(), pub.getPhone());
        }
        System.out.println(fmt);
    }
}
