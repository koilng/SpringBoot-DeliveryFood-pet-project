package onlineshop.service;

import onlineshop.repository.PersonRepository;
import onlineshop.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService<Person> {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        return personRepository.getById(id);
    }

    @Override
    public List<Person> index() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
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
