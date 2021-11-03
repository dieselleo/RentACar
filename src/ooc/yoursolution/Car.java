/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.util.HashMap;
import java.util.Map;
import ooc.enums.Make;
import ooc.enums.Month;

/**
 *
 * @author diese
 */
public class Car implements CarInterface{
    
    private int id;
    private Make make;
    private double rate;
    private Map<Month, boolean[]> availability;
    
    public Car (int id, Make make, double rate) {
        this.id = id;
        this.make = make;
        this.rate = rate;
        this.availability = new HashMap<>();
        
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", make=" + make + ", rate=" + rate + '}';
    }

    
    
    @Override
    public Map<Month, boolean[]> createAvailability() {
        
        Month[] allMonths = Month.values();
        for (Month m : allMonths){
            int numDays = m.getNumberOfDays();
            boolean[] days = new boolean[numDays];
            for (int d = 0; d < numDays; d++){
                days[d] = true;  
                availability.put(m, days); 
            }
        }
        
        return availability;
    }

    @Override
    public Make getMake() {
        return make;
    }

    @Override
    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public Map<Month, boolean[]> getAvailability() {
        return availability;
    }

    @Override
    public void setAvailability(Map<Month, boolean[]> availability) {
        this.availability = availability;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAvailable(Month month, int day) {
        
        return availability.get(month)[day-1];
        
        }

    @Override
    public boolean book(Month month, int day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
