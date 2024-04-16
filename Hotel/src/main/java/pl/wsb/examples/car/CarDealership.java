package pl.wsb.examples.car;

import java.util.ArrayList;
import java.util.List;

// class that implements an interface //
public class CarDealership implements Showroom {

    private String name;

    // collection for cars //
    private List<Car> cars;


    // constructor //
    public CarDealership(String name) {
        this.name = name;

        // collection initialization //
        this.cars = new ArrayList<>();
    }

    // needed getters //
    public String getName() {
        return name;
    }

    // other methods //
    public int getNumberOfCars() {
        // null check //
        if (this.cars == null) {
            // return 0 when the cars collection does not exist //
            return 0;
        }

        // otherwise return size of the cars collection //
        return this.cars.size();
    }

    // method that must be implemented because th class implements an interface //
    @Override
    public void addCar(Car newCar) {
        // null check //
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }

        this.cars.add(newCar);
    }

    @Override
    public List<String> getAllSummaries() {
        // null check //
        if (this.cars == null) {
            // return empty list when the cars collection does not exist //
            return new ArrayList<>();
        }

        List<String> summaries = new ArrayList<>(this.cars.size());
        // for each loop in action //
        for (Car car : this.cars) {
            String carSummary = car.getCarSummary();
            summaries.add(carSummary);
        }
        return summaries;
    }
}
