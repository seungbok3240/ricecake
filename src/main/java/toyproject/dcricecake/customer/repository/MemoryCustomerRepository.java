package toyproject.dcricecake.customer.repository;

import org.springframework.stereotype.Repository;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.CustomerSignupForm;

import java.util.*;

@Repository
public class MemoryCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Customer save(CustomerSignupForm form) {
        Customer customer = new Customer();
        customer.setId(sequence++);
        customer.setLoginId(form.getLoginId());
        customer.setPassword(form.getPassword());

        store.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(s -> s.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    // 테스트 케이스용
    public void clearAll() {
        store.clear();
    }
}
