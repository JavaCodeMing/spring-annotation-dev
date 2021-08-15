package org.example.autowired.entity;

/**
 * @author: dengzm
 * @date: 2021-07-06 23:25:03
 */
public class Color {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}
