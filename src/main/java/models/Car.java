package models;

import java.io.Serializable;

/**
 * Created by Марсель on 08.02.2017.
 */
public class Car implements Serializable {

    private int price;
    private String model;
    private String regNum;

    public Car(int price, String model, String regNum) {
        this.price = price;
        this.model = model;
        this.regNum = regNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return regNum != null ? regNum.equals(car.regNum) : car.regNum == null;
    }

    @Override
    public int hashCode() {
        return regNum != null ? regNum.hashCode() : 0;
    }
}
