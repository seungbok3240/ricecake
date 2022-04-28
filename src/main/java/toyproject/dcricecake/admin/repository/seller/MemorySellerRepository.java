package toyproject.dcricecake.admin.repository.seller;

import org.springframework.stereotype.Repository;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;

import java.util.*;

@Repository
public class MemorySellerRepository implements SellerRepository {

    private final Map<Long, Seller> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public void save(SellerSignupForm form) {
        Seller seller = new Seller();
        seller.setId(sequence++);
        seller.setLoginId(form.getLoginId());
        seller.setPassword(form.getPassword());

        store.put(seller.getId(), seller);
    }

    @Override
    public Optional<Seller> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(s -> s.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public List<Seller> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
