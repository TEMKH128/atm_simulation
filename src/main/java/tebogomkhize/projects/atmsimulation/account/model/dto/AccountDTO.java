package tebogomkhize.projects.atmsimulation.account.model.dto;


public class AccountDTO {
    String age;
    String name;
    String email;

    public AccountDTO(String age, String name, String email) {
        this.age = age;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters.
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
