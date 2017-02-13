package models;

import datamanager.DataManager;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Марсель on 08.02.2017.
 */
public class Store {

    private HashMap<Order, Client> contractList = new HashMap<>(256);
    private HashSet<Car> cars = new HashSet<>(256);
    private HashSet<Client> clients = new HashSet<>();
    private static final String FILE_CONTRACTS = "contracts.dat";
    private static final String FILE_CARS = "cars.dat";
    private static final String FILE_CLIENTS = "clients.dat";

    public void createCar(int price, String model, String regNum) {
        Car car = new Car(price, model, regNum);
        cars.add(car);
    }

    public void sellCar(String model, String fullname, String phoneNumber) throws CarNotFoundException {
        Client client = new Client(fullname, phoneNumber);
        clients.add(client);

        Car car = null;
        for (Car c : cars) {
            if (c.getModel().equals(model)) {
                car = c;
                break;
            }
        }
        if (car == null) {
            throw new CarNotFoundException();
        } else {
            Random random = new Random();
            Order order = new Order(car, car.getPrice()*2, random.nextLong(), (short) 80);
            contractList.put(order, client);
            cars.remove(car);
        }
    }

    public List<Order> getOrders() {
        List<Order> list = new ArrayList<>();
        for (Order order : contractList.keySet()) {
            list.add(order);
//           System.out.println(order);
        }

        return list;
    }

    public List<Car> getFreeCars() {
        List<Car> list = new ArrayList<Car>();
        for (Car car : cars) {
            list.add(car);
//            System.out.println(car.getModel());
        }

        return list;
    }

    public Order getFirstOrder() {
        return contractList.keySet().iterator().next();
    }

    public void save() {
        DataManager.serialize(clients, FILE_CLIENTS);
        DataManager.serialize(cars, FILE_CARS);
        DataManager.serialize(contractList, FILE_CONTRACTS);
    }

    public void recover() {
        ArrayList<Serializable> list = new ArrayList<>();
        DataManager.deserialize(list, FILE_CARS);
        for (Serializable car:
             list) {
            cars.add((Car) car);
        }

        ArrayList<Serializable> list2 = new ArrayList<>();
        DataManager.deserialize(list2, FILE_CLIENTS);
        for (Serializable client:
                list2) {
            clients.add((Client) client);
        }

        DataManager.deserialize(contractList, FILE_CONTRACTS);
    }
}
