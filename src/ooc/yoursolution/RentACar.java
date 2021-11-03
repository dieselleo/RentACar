/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//                System.out.println(c.toString());
//                System.out.println("Month available in this car "+c.getAvailability().size());
//                System.out.println("Days available on the "+month+" month "+c.getAvailability().get(month).length);
                for (int d = day; d <= lengthOfRent; d++) {
                    available = c.isAvailable(month, (d));
                    if (available == false){
                        d = lengthOfRent;
                    }
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
                available = checkAvailability(month, day, make, lengthOfRent);
                if (available == true) {
//                    System.out.println("good return");
                    return c.getId();
                }
            }
        }
//        System.out.println("no car available");

        return 0;
    }

    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
//        System.out.println("Trying booking");
        boolean booked = false;
        int carToBook = getCarAvailable(month, day, make, lengthOfRent);
        for (CarInterface c : cars) {
            if(carToBook == c.getId()){
                for (int d = day; d <= lengthOfRent; d++) {
//                    System.out.println("trying to book day "+d);
                    booked = c.book(month, d);
                    if (booked == true) {
//                        System.out.println("all good back in the method, keep going");
                    } else {
                        d = lengthOfRent;
                    }
                }
            }
        }

        return booked;
    }

    @Override
    public int getNumberOfCars() {
        return cars.size();
    }

}
