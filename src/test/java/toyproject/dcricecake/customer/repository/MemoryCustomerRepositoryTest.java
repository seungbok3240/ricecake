package toyproject.dcricecake.customer.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.CustomerSignupForm;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemoryCustomerRepositoryTest {
    MemoryCustomerRepository repository = new MemoryCustomerRepository();

    @AfterEach
    void after() {
        repository.clearAll();
    }

    @Test
    void save_findByLoginId() {
        CustomerSignupForm customerForm = new CustomerSignupForm();
        customerForm.setLoginId("test");
        customerForm.setPassword("test");

        Customer saveCustomer = repository.save(customerForm);

        Optional<Customer> findCustomer = repository.findByLoginId(customerForm.getLoginId());
        Customer customer = findCustomer.get();
        assertThat(customer).isEqualTo(saveCustomer);
    }

    @Test
    void findAll() {
        CustomerSignupForm customerForm = new CustomerSignupForm();
        customerForm.setLoginId("test");
        customerForm.setPassword("test");

        CustomerSignupForm customerForm2 = new CustomerSignupForm();
        customerForm.setLoginId("test2");
        customerForm.setPassword("test2");

        repository.save(customerForm);
        repository.save(customerForm2);

        List<Customer> customers = repository.findAll();
        assertThat(customers.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        CustomerSignupForm customerForm = new CustomerSignupForm();
        customerForm.setLoginId("test");
        customerForm.setPassword("test");

        Customer saveCustomer = repository.save(customerForm);

        repository.delete(saveCustomer.getId());

        assertThatThrownBy(() -> repository.findByLoginId(saveCustomer.getLoginId()).get()).isInstanceOf(NoSuchElementException.class);
    }
}