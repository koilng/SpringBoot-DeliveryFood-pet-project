package onlineshop.dao;

import onlineshop.models.Item;
import onlineshop.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ItemsDAO {
    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM items", new BeanPropertyRowMapper<>(Person.class));
    }

    public Item show(String header) {
        return jdbcTemplate.query("SELECT * FROM items WHERE header=?", new Object[]{header}, new BeanPropertyRowMapper<>(Item.class))
                .stream().findAny().orElse(null);
    }

    public void save(Item item) {
        jdbcTemplate.update("INSERT INTO items VALUES(?, ?, ?, ?)", item.getQuantity(), item.getCategory(), item.getHeader(), item.getDescription());
    }

    public void update(String header, Item updatedItem) {
        jdbcTemplate.update("UPDATE items SET quantity=?, category=?, description=? WHERE header=?", updatedItem.getQuantity(), updatedItem.getCategory(),
                updatedItem.getDescription(), updatedItem.getHeader());
    }

    public void delete(String header) {
        jdbcTemplate.update("DELETE FROM items WHERE header=?", header);
    }*/
}
