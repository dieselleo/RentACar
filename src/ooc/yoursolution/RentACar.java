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
                    } else {
                        return available;
                    }
                }
            }
        }

        return available;

    }

    @Override
    public int getCarAvailable(Month month, int day, Make make, int lengthOfRent) {
        boolean available = false;
        int carToBook=0;
        int realLength = lengthOfRent+day;
        for (CarInterface c : cars) {
            carToBook = c.getId();
//            System.out.println("Got car ID "+carToBook+" Make "+c.getMake());
            if(c.getMake().equals(make)) {
                for (int d = day; d < realLength; d++) {
                    available = c.isAvailable(month, (d));
                    if (available == false){
                        d = realLength;
                    } else {
                        return c.getId();
                    }
                }
            }
        }
//        System.out.println("no car available");

        return carToBook;
    }

    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
        if (day > month.getNumberOfDays() || month.getNumberOfDays() < (day+lengthOfRent)){
            return false;
        }
//        System.out.println("Trying booking");
        boolean booked = false;
        int realLength = lengthOfRent+day;
        int carToBook = getCarAvailable(month, day, make, lengthOfRent);
//        System.out.println("returned available car");
        for (CarInterface c : cars) {
            if(c.getMake().equals(make)) {
                if(carToBook == c.getId()){
                for (int d = day; d < realLength; d++) {
//                    System.out.println("trying to book day "+d);
                    booked = c.book(month, d);
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
