package pl.wsb.hotel;

import java.time.LocalDate;
import java.time.Period;

public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    // Pola dodane przez nas
    private Boolean smoker;
    private String gender;
    private String number;
    private String email;

    public Client(String id, String firstName, String lastName, LocalDate birthDate)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;

    }
    
    public Client(String id, String firstName, String lastName, LocalDate birthDate, Boolean smoker, String gender, String number, String email)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.smoker = smoker;
        this.gender = gender;
        this.number = number;
        this.email = email;
    }
    
    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {

        this.birthDate = birthDate;
    }

    public int getAge() {

        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public String getFullName() {

        return firstName + " " + lastName;
    }
    public void setSmoker(boolean smoke) {
        this.smoker = smoke;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
