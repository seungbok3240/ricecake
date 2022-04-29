package toyproject.dcricecake.customer.repository;

import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.CustomerSignupForm;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(CustomerSignupForm form);

    void delete(Long id);

    Optional<Customer> findByLoginId(String loginId);

    List<Customer> findAll();
}
