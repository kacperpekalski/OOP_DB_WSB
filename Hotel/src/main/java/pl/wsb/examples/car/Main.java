package pl.wsb.examples.car;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // creating 2 car dealership instances //
        CarDealership abcAuto = new CarDealership("ABC Auto");

        // polymorphism in action! look at the variable type! //
        Showroom premiumCars = new CarDealership("Premium Cars");

        // creating 3 car instances //
        Car whiteFordFocus = new Car("Ford Focus", LocalDate.of(2012, 6, 1), Color.WHITE);
        Car redFiatPanda = new Car("Fiat Panda", LocalDate.of(2002, 10, 15), Color.RED);

        // watch out! different constructor is used! //
        Car blackFordMondeo = new Car("Ford Mondeo", LocalDate.of(2018, 1, 1), Color.BLACK, "windshield is broken");


        // adding 3 cars to ABC Auto dealership //
        abcAuto.addCar(whiteFordFocus);
        abcAuto.addCar(blackFordMondeo);
        abcAuto.addCar(redFiatPanda);

        // adding 2 cars to Premium Cars dealership //
        premiumCars.addCar(whiteFordFocus);
        premiumCars.addCar(blackFordMondeo);

        // printing some info about dealers //
        System.out.println("FIRST DEALERSHIP");
        System.out.println("name: " + abcAuto.getName());
        System.out.println("number of cars:" + abcAuto.getNumberOfCars());
        for (String summary : abcAuto.getAllSummaries()) {
            System.out.println(summary);
        }

        System.out.println();
        System.out.println("SECOND DEALERSHIP");
        // second dealership is assigned to the "Showroom" variable type //
        // that's why we can invoke only the methods declared in the interface //
        for (String summary : premiumCars.getAllSummaries()) {
            System.out.println(summary);
        }
    }
}
