package pl.wsb.hotel;

public class TimeService {
    public class TimeService extends SpecialService {
        @Override
        public void orderService() {
            System.out.println(java.time.LocalTime.now());
        }

        // implementacja dodatkowych metod abstrakcyjnych
        @Override
        public void additionalMethod1() {
            // implementacja metody
        }

        @Override
        public String additionalMethod2(int param) {
            // implementacja metody
            return null;
        }

        @Override
        public boolean additionalMethod3(String param, int param2) {
            // implementacja metody
            return false;
        }
}
