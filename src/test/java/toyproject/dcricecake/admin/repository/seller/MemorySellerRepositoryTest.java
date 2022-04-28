package toyproject.dcricecake.admin.repository.seller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemorySellerRepositoryTest {

    MemorySellerRepository repository = new MemorySellerRepository();

    @AfterEach
    void after() {
        repository.clearAll();
    }

    @Test
    void save_findByLoginId() {
        SellerSignupForm sellerForm = new SellerSignupForm();
        sellerForm.setLoginId("test");
        sellerForm.setPassword("test");

        Seller saveSeller = repository.save(sellerForm);

        Optional<Seller> findSeller = repository.findByLoginId(sellerForm.getLoginId());
        Seller seller = findSeller.get();
        assertThat(seller).isEqualTo(saveSeller);
    }

    @Test
    void findAll() {
        SellerSignupForm sellerForm = new SellerSignupForm();
        sellerForm.setLoginId("test");
        sellerForm.setPassword("test");

        SellerSignupForm sellerForm2 = new SellerSignupForm();
        sellerForm.setLoginId("test2");
        sellerForm.setPassword("test2");

        repository.save(sellerForm);
        repository.save(sellerForm2);

        List<Seller> sellers = repository.findAll();
        assertThat(sellers.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        SellerSignupForm sellerForm = new SellerSignupForm();
        sellerForm.setLoginId("test");
        sellerForm.setPassword("test");

        Seller saveSeller = repository.save(sellerForm);

        repository.delete(saveSeller.getId());

        assertThatThrownBy(() -> repository.findByLoginId(saveSeller.getLoginId()).get()).isInstanceOf(NoSuchElementException.class);
    }

}