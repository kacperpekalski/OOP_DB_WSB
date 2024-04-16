package pl.wsb.hotel.client;

import java.time.LocalDate;

public class PremiumClient extends Client {
    private PremiumClientType premiumAccountType;

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate, Boolean smoker, String gender, String number, String email) {
        super(id, firstName, lastName, birthDate, smoker, gender, number, email);
        this.premiumAccountType = PremiumClientType.PREMIUM;
    }

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate, Boolean smoker, String gender, String number, String email, PremiumClientType premiumAccountType) {
        super(id, firstName, lastName, birthDate, smoker, gender, number, email);
        this.premiumAccountType = premiumAccountType;
    }

    @Override
    public String getFullName() {
        return "[premium] " + super.getFullName();
    }

    // getter and setter for premiumAccountType
    public PremiumClientType getPremiumAccountType() {
        return premiumAccountType;
    }

    public void setPremiumAccountType(PremiumClientType premiumAccountType) {
        this.premiumAccountType = premiumAccountType;
    }

}