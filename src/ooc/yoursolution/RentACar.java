/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.util.ArrayList;
import java.util.List;
import ooc.enums.Make;
import ooc.enums.Month;

/**
 *
 * @author diese
 */
public class RentACar implements RentACarInterface {

    List<CarInterface> cars;
    String name;

    public RentACar(String name) {

        this.name = name;
        this.cars = new ArrayList<CarInterface>();

    }

    @Override
    public List<CarInterface> getCars() {
        return cars;
    }

    @Override
    public void setCars(List<CarInterface> car) {
        this.cars.addAll(car);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean checkAvailability(Month month, int day, Make make, int lengthOfRent) {
        boolean available = false;
        for (CarInterface c : cars) {
            if (c.getMake().equals(make)) {
                for (int d = day; d < lengthOfRent; d++) {
                    available = c.isAvailable(month, (d));
                }
            }
        }

        return available;

    }

    @Override
    public int getCarAvailable(Month month, int day, Make make, int lengthOfRent) {
        boolean available = false;
        for (CarInterface c : cars) {
            if (c.getMake().equals(make)) {
                for (int d = day; d < lengthOfRent; d++) {
                    available = c.isAvailable(month, (d));
                    if (available == true) {
                        return c.getId();
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfCars() {
        return cars.size();
    }

}
