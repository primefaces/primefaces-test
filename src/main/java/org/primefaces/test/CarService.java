package org.primefaces.test;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CarService implements Serializable {

    private final static String[] colors;

    private final static String[] brands;

    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";

        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }

    private int rowCount;
    private List<Car> cars;

    public List<Car> createCars(int size) {
        cars = new ArrayList<Car>();
        for(int i = 0 ; i < size ; i++) {
            cars.add(new Car(new Integer(i).toString(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }
        rowCount = cars.size();
        return cars;
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }

    private int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }

    private boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }

    public List<String> getColors() {
        return Arrays.asList(colors);
    }

    public List<String> getBrands() {
        return Arrays.asList(brands);
    }

    public List<Car> getDataSet(int first, int pageSize) {
        int toIndex = first + pageSize;

        if (first >= rowCount) {
            return new ArrayList<Car>();
        }

        if (toIndex >= rowCount) {
            toIndex = rowCount;
        }

        if (toIndex <= first) {
            return new ArrayList<Car>();
        } else {
            return cars.subList(first, toIndex);
        }
    }
}