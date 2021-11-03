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
//        System.out.println("trying position "+(day-1));
//        System.out.println("this is the method isAvailable ->"+availability.get(month)[day-1]);
        return availability.get(month)[day-1];
        
        }

    @Override
    public boolean book(Month month, int day) {
        
//        System.out.println("trying isAvailable inside method book");
        if(isAvailable(month, day) == true){
            boolean[] daysToBook = new boolean[this.availability.get(month).length];
//            System.out.println("daysToBook created with size of "+daysToBook.length);
            int count=0;
            for (boolean b : this.availability.get(month)){
                        daysToBook[count] = b;
//                        System.out.println("Position "+count+" value "+b);
                        count++;
                    }
            daysToBook[day-1] = false;
//            System.out.println("update position"+(day-1));
//            System.out.println("daysToBook populated and with size of "+daysToBook.length);
//            System.out.println("booking started");
            this.availability.replace(month, daysToBook);
//            System.out.println("all good, going back to method ->" + true);
            return true;
        }
        
        return false;
    }
    
}
