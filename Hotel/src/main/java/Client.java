import java.time.LocalDate;
import java.time.Period;

public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    // 3- 5 dodatkowych pól, nieujętch na diagramie klas


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
}