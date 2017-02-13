package models;

import java.io.Serializable;

/**
 * Created by Марсель on 08.02.2017.
 */
public class Order implements Serializable {

    private Car car;
    private int sum;
    private long orderNumber;
    private short orgNumber;

    public Order(Car car, int sum, long orderNumber, short orgNumber) {
        this.car = car;
        this.sum = sum;
        this.orderNumber = orderNumber;
        this.orgNumber = orgNumber;
    }

    public short getOrgNumber() {

        return orgNumber;
    }

    public void setOrgNumber(short orgNumber) {
        this.orgNumber = orgNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderNumber != order.orderNumber) return false;
        return orgNumber == order.orgNumber;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderNumber ^ (orderNumber >>> 32));
        result = 31 * result + (int) orgNumber;
        return result;
    }

    @Override
    public String toString() {
        return car.getModel() +
                " regNum: " + car.getRegNum() +
                " sum: " + sum +
                " orderNum: " + orderNumber +
                " orgNum: " + orgNumber;
    }
}
