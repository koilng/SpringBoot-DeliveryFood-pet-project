package onlineshop.service;

import onlineshop.repository.PersonRepository;
import onlineshop.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        return personRepository.getById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
