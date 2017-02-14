package models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Марсель on 13.02.2017.
 */
class StoreTest {
    private static Store store;

    @BeforeAll
    public static void initStore() {
        store = new Store();
        assertNotNull(store);
    }

    @AfterAll
    public static void finish() {
        System.out.println("Finished.");
    }

    @org.junit.jupiter.api.Test
    void createCar() {
        Store store = new Store();
        Car car = new Car(100, "Lada", "ABC");
        store.createCar(100, "Lada", "ABC");

        assertNotNull(store.getFreeCars());

        assertTrue(store.getFreeCars().size()>0);

        store.getFreeCars().stream().forEach((car1) -> {
            assertEquals(100, car1.getPrice());
            assertEquals("Lada", car1.getModel());
            assertEquals("ABC", car1.getRegNum());
        });
    }

    @org.junit.jupiter.api.Test
    void sellCar() throws CarNotFoundException {
        Store store = new Store();
        assertThrows(CarNotFoundException.class, () -> {
            store.sellCar("Lada", "Artem", "123");
        });

        store.createCar(100, "Lada", "ABC");
        store.sellCar("Lada", "Artem", "123");
        assertTrue(store.getFreeCars().size() == 0);

        Car car = new Car(100, "Lada", "ABC");
        assertTrue(store.getOrders().stream().filter(
                (order) -> order.getCar().getModel().equals("Lada")
                    && order.getCar().getRegNum().equals("ABC")
                    && order.getCar().getPrice() == 100
            ).count() == 1
        );

        assertTrue(store.getContractList().size() == 1);
        assertTrue(store.getContractList().values().stream().filter(
                (client) -> client.getFullname().equals("Artem")
                    && client.getPhoneNumber().equals("123")
            ).count() == 1
        );
    }

    @org.junit.jupiter.api.Test
    void getContractList() {
        Store store = new Store();
        assertNotNull(store.getContractList());
        assertTrue(store.getContractList().size() == 0);
    }

    @org.junit.jupiter.api.Test
    void getOrders() {

    }

    @org.junit.jupiter.api.Test
    void getFreeCars() {

    }

    @org.junit.jupiter.api.Test
    void getFirstOrder() {
        Store store = new Store();
    }

    @org.junit.jupiter.api.Test
    void save() {

    }

    @org.junit.jupiter.api.Test
    void recover() {

    }

}