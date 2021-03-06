package onlineshop.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    @Column(name = "role")
    private Role role;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    @Column(name = "status")
    private Status status;

    public Person() {
        status = Status.ACTIVE;
        role = Role.USER;
    }
}
