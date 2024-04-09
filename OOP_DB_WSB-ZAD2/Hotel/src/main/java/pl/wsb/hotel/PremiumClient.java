package pl.wsb.hotel;

public class PremiumClient extends Client {
    private PremiumAccountType premiumAccountType;

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate, Boolean smoker, String gender, String number, String email, PremiumAccountType premiumAccountType) {
        super(id, firstName, lastName, birthDate, smoker, gender, number, email);
        this.premiumAccountType = premiumAccountType;
    }

    @Override
    public String getFullName() {
        return "[premium] " + super.getFullName();
    }

    // getter i setter dla premiumAccountType
    public PremiumAccountType getPremiumAccountType() {
        return premiumAccountType;
    }

    public void setPremiumAccountType(PremiumAccountType premiumAccountType) {
        this.premiumAccountType = premiumAccountType;
    }
}