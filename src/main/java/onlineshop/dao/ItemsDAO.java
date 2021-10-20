package onlineshop.dao;

import onlineshop.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemsDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> index() {
        return jdbcTemplate.query("SELECT id, quantity, category, name, description FROM items", new BeanPropertyRowMapper<>(Item.class));
    }

    public Item show(String name) {
        return jdbcTemplate.query("SELECT FROM items WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Item.class))
                .stream().findAny().orElse(null);
    }

    public void save(Item item) {
        jdbcTemplate.update("INSERT INTO items (quantity, category, name, description) VALUES (?, ?, ?, ?)", item.getQuantity(), item.getCategory(), item.getName(), item.getDescription());
    }

    public void update(String name, Item updatedItem) {
        jdbcTemplate.update("UPDATE items SET quantity=?, category=?, description=? WHERE name=?", updatedItem.getQuantity(), updatedItem.getCategory(),
                updatedItem.getDescription(), updatedItem.getName());
    }

    public void delete(String name) {
        jdbcTemplate.update("DELETE FROM items WHERE name=?", name);
    }
}
