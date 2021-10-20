package onlineshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    PersonDAO personDAO;
    ItemsDAO itemsDAO;

    @Autowired
    public ShopService(PersonDAO personDAO, ItemsDAO itemsDAO) {
        this.personDAO = personDAO;
        this.itemsDAO = itemsDAO;
    }

    public PersonDAO personDAO() {
        return personDAO;
    }

    public ItemsDAO itemsDAO() {
        return itemsDAO;
    }
}
