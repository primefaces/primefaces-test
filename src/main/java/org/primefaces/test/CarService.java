/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named(value = "carService")
@ApplicationScoped
public class CarService {
     
    private final static String[] COLORS;
     
    private final static String[] BRANDS;
     
    static {
        COLORS = new String[10];
        COLORS[0] = "Black";
        COLORS[1] = "White";
        COLORS[2] = "Green";
        COLORS[3] = "Red";
        COLORS[4] = "Blue";
        COLORS[5] = "Orange";
        COLORS[6] = "Silver";
        COLORS[7] = "Yellow";
        COLORS[8] = "Brown";
        COLORS[9] = "Maroon";
         
        BRANDS = new String[10];
        BRANDS[0] = "BMW";
        BRANDS[1] = "Mercedes";
        BRANDS[2] = "Volvo";
        BRANDS[3] = "Audi";
        BRANDS[4] = "Renault";
        BRANDS[5] = "Fiat";
        BRANDS[6] = "Volkswagen";
        BRANDS[7] = "Honda";
        BRANDS[8] = "Jaguar";
        BRANDS[9] = "Ford";
    }
     
    public List<Car> createCars(int size) {
        List<Car> list = new ArrayList<>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }
         
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     
    private String getRandomColor() {
        return COLORS[(int) (Math.random() * 10)];
    }
     
    private String getRandomBrand() {
        return BRANDS[(int) (Math.random() * 10)];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return Math.random() > 0.5;
    }
 
    public List<String> getColors() {
        return Arrays.asList(COLORS);
    }
     
    public List<String> getBrands() {
        return Arrays.asList(BRANDS);
    }
}