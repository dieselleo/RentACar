/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import ooc.enums.Make;

/**
 *
 * @author diese
 */
public class BookingSystem implements BookingSystemInterface{

    @Override
    public RentACarInterface setupRentACar(BufferedReader in) throws IOException {
        
        String name = in.readLine();
        
        RentACarInterface carRental = new RentACar(name);
        ArrayList<CarInterface> carsList = new ArrayList<>();
        
        String line = in.readLine();
        Make[] allMakes = Make.values();
        Make make=null;
        double rate;
        int id = 1;
        int qtdCars;
        int count;
        
        while (line != null){
            count=0;
            String[] content = line.split(":");
            
            for(Make m : allMakes){
                if(m.toString().equalsIgnoreCase(content[0])){
                    make = m;
                }
            }
            
            rate = Double.parseDouble(content[1]);
            qtdCars = Integer.parseInt(content[2]);
            
            do {
                Car cars = new Car (id, make, rate);
                cars.createAvailability();
                count++;
                id++;
                carsList.add(cars);
                
            } while (count < qtdCars);
                      
            
            line = in.readLine();
        }
        
        carRental.setCars(carsList);
        
        return carRental;
    }
    
}
