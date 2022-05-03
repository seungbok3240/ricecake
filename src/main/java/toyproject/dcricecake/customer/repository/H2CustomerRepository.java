package toyproject.dcricecake.customer.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.CustomerSignupForm;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class H2CustomerRepository implements CustomerRepository{

    private final JdbcTemplate template;

    public H2CustomerRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(CustomerSignupForm form) {
        String sql = "insert into customer(loginId, password) values (?, ?)";
        template.update(sql, form.getLoginId(), form.getPassword());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete customer where customer_id = ?";
        template.update(sql, id);
    }

    @Override
    public Optional<Customer> findByLoginId(String loginId) {
        String sql = "select * from customer where loginId = ?";
        return Optional.of(template.queryForObject(sql, customerMapper(), loginId));
    }

    private RowMapper<Customer> customerMapper() {
        return (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setId(rs.getLong("customer_id"));
            customer.setLoginId(rs.getString("loginId"));
            customer.setPassword(rs.getString("password"));
            return customer;
        };
    }

    @Override
    public List<Customer> findAll() {
        String sql = "select * from customer";
        return template.query(sql, customerMapper());
    }
}
