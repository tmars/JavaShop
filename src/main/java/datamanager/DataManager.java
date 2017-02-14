package datamanager;

import models.Order;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Марсель on 08.02.2017.
 */
public class DataManager {
    private static final Logger logger = Logger.getLogger(DataManager.class);
    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }
    public static void serialize(Collection<? extends Serializable> list, String filename) {
        for (Serializable object: list) {
            try (FileOutputStream fos = new FileOutputStream(filename)) {
                ObjectOutputStream bos = new ObjectOutputStream(fos);
                bos.writeObject(object);
            } catch (FileNotFoundException e) {
                logger.error("file not found " + filename);
//                e.printStackTrace();
            } catch (IOException e) {
                logger.error("io exception " + filename);
//                e.printStackTrace();
            }
        }
    }

    public static void serialize(Map<? extends Serializable, ? extends Serializable> map, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            ObjectOutputStream bos = new ObjectOutputStream(fos);
            bos.writeObject(map);
        } catch (FileNotFoundException e) {
            logger.error("file not found " + filename);
//            e.printStackTrace();
        } catch (IOException e) {
            logger.error("io exception " + filename);
//            e.printStackTrace();
        }
    }

    public static void deserialize(Collection<Serializable> list, String filename) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            ObjectInputStream bis = new ObjectInputStream(fis);
            Serializable object;
            while ((object = (Serializable) bis.readObject()) != null) {
                list.add(object);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize(Map<? extends Serializable, ? extends Serializable> map, String filename) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            ObjectInputStream bis = new ObjectInputStream(fis);
            map = (Map) bis.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
