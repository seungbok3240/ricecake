package toyproject.dcricecake.admin.repository.seller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class H2SellerRepository implements SellerRepository{

    private final JdbcTemplate template;

    public H2SellerRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public void save(SellerSignupForm form) {
        String sql = "insert into seller(loginId, password) values (?, ?)";
        template.update(sql, form.getLoginId(), form.getPassword());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete seller where seller_id = ?";
        template.update(sql, id);
    }

    @Override
    public Optional<Seller> findByLoginId(String loginId) {
        String sql = "select * from seller where loginId = ?";
        return Optional.of(template.queryForObject(sql, sellerRowMapper(), loginId));
    }

    private RowMapper<Seller> sellerRowMapper() {
        return (rs, rowNum) -> {
            Seller seller = new Seller();
            seller.setId(rs.getLong("seller_id"));
            seller.setLoginId(rs.getString("loginId"));
            seller.setPassword(rs.getString("password"));
            return seller;
        };
    }

    @Override
    public List<Seller> findAll() {
        String sql = "select * from seller";
        return template.query(sql, sellerRowMapper());
    }
}
