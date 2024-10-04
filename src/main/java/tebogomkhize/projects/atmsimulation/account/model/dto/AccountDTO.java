package tebogomkhize.projects.atmsimulation.account.model.dto;


public class AccountDTO {
    int age;
    String email;
    String firstName;
    String lastName;

    public AccountDTO(
        int age, String firstName, String lastName, String email) {

        this.age = age;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    // Getters and Setters.
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
