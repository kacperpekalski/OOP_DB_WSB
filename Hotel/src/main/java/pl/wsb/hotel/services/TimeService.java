package pl.wsb.hotel.services;


import pl.wsb.hotel.services.SpecialService;

public class TimeService extends SpecialService {

        protected TimeService(String name, double price) {
                super(name, price);
        }

        @Override
        public void orderService() {
            // print current time
            System.out.println("Current time: " + java.time.LocalTime.now());
        }

        @Override
        public void checkAvailability() {
            // message that time service is available
            System.out.println("Time service is available");
        }

        @Override
        public void updatePrice(double newPrice) {
            // message that price has been updated
            System.out.println("Price has been updated");
        }

}
