package datamanager;

import models.Car;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by Марсель on 13.02.2017.
 */
class DataManagerTest {
    @Test
    void serialize() throws IOException {
        Car car = new Car(100, "Lada", "ABC");

        ObjectOutputStream oos = mock(ObjectOutputStream.class);
//        when(oos.writeObject(car)).thenReturn(car);
    }

    @Test
    void serialize1() {

    }

    @Test
    void deserialize() {

    }

    @Test
    void deserialize1() {

    }

}