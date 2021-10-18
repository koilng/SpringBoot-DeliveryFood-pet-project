package onlineshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    PersonDAO personDAO;

    @Autowired
    public ShopService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public PersonDAO personDAO() {
        return personDAO;
    }
}
