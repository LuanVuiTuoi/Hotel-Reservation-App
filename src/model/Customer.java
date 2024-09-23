package model;
import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){
        if(!isValidEmail(email)){
            throw new IllegalArgumentException("Account Creation Failed:\nEmail is not valid! Please use format name@domain.com");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    private boolean isValidEmail(String email){
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                        "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)$";
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString(){
        return String.format("First Name: %s%n" +
                        "Last Name: %s%n" +
                        "Email: %s%n",
                        firstName,
                        lastName,
                        email);
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
