package onlineshop.RepositoryTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Optional;
import onlineshop.model.Person;
import onlineshop.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  private Person person;
  private String email;
  private String name;

  @BeforeEach
  void setUp() {
    person = new Person();
    email = "user@mail.ru";
    name = "user";
    person.setEmail(email);
    person.setName(name);
    personRepository.save(person);
  }

  @AfterEach
  void tearDown() {
    personRepository.deleteAll();
  }

  @Test
  void checkFindByEmail() {
    Optional<Person> testPerson = personRepository.findByEmail(email);

    assertThat(person).isEqualTo(testPerson.get());
  }

  @Test
  void checkFindByName() {
    Optional<Person> testPerson = personRepository.findByName(name);

    assertThat(person).isEqualTo(testPerson.get());
  }
}
