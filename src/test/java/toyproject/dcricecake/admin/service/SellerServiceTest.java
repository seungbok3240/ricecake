package toyproject.dcricecake.admin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;
import toyproject.dcricecake.admin.repository.seller.MemorySellerRepository;
import toyproject.dcricecake.admin.repository.seller.SellerRepository;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SellerServiceTest {

    private final SellerRepository repository = new MemorySellerRepository();
    private final SellerService service = new SellerService(repository);

    @Test
    void serviceTest() {

        // sign up
        SellerSignupForm form = new SellerSignupForm();
        form.setLoginId("test");
        form.setPassword("test");
        form.setCheckPassword("test");

        Seller singupSeller = service.singup(form);
        Seller findSeller = service.findByLoginId(singupSeller.getLoginId());
        assertThat(findSeller).isEqualTo(singupSeller);

        // login
        Seller loginSeller = service.login(singupSeller.getLoginId(), singupSeller.getPassword());
        assertThat(loginSeller).isEqualTo(singupSeller);

        // find seller
        Seller findSeller2 = service.findByLoginId(singupSeller.getLoginId());
        assertThat(findSeller2).isEqualTo(singupSeller);

        // delete
        service.delete(singupSeller.getId());
        assertThatThrownBy(() -> service.findByLoginId(singupSeller.getLoginId())).isInstanceOf(NoSuchElementException.class);
    }

}