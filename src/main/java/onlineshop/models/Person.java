package onlineshop.models;

/**
 * @author Neil Alishev
 */
public class Person {
    private String id;

    private String name;

    private String role;

    private String email;

    private String password;

    public Person() {

    }

    public Person(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
