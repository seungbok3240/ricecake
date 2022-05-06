package toyproject.dcricecake.admin.repository.item;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

//@Repository
public class H2ItemRepository implements ItemRepository{

    private final JdbcTemplate template;

    public H2ItemRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public Long save(Item item) {
        String sql = "insert into item(name, price, quantity) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ITEM_ID"});
            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Item findById(Long id) {
        String sql = "select * from item where item_id = ?";
        return template.queryForObject(sql, itemRowMapper(), id);
    }

    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("item_id"));
            item.setItemName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        };
    }

    @Override
    public List<Item> findAll() {
        String sql = "select * from item";
        return template.query(sql, itemRowMapper());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from item where item_id = ?";
        template.update(sql, id);
    }

    @Override
    public void update(Long itemId, ItemUpdateForm form) {
        String sql = "update item set (name, price, quantity) = (?, ?, ?) where item_id = ?";
        template.update(sql, form.getItemName(), form.getPrice(), form.getQuantity(), itemId);
    }
}
