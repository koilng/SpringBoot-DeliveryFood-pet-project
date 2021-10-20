package onlineshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import onlineshop.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT id, role, name, email, password, status FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(String name) {
        return jdbcTemplate.query("SELECT name, email, password FROM person WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, email, password) VALUES (?, ?, ?)", person.getName(), person.getEmail(),
                new BCryptPasswordEncoder(12).encode(person.getPassword()));
    }

    public void update(String name, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, email=?, password=? WHERE name=?",
                updatedPerson.getName(), updatedPerson.getEmail(),
                new BCryptPasswordEncoder(12).encode(updatedPerson.getPassword()), name);
    }

    public void delete(String name) {
        jdbcTemplate.update("DELETE FROM person WHERE name=?", name);
    }
}
