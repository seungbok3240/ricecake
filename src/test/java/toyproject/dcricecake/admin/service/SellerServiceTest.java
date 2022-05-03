package toyproject.dcricecake.admin.service;

import org.junit.jupiter.api.Test;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;
import toyproject.dcricecake.admin.repository.seller.MemorySellerRepository;
import toyproject.dcricecake.admin.repository.seller.SellerRepository;

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

//        service.singup(form);
//        Seller findSeller = service.findByLoginId(singupSeller.getLoginId());
//        assertThat(findSeller).isEqualTo(singupSeller);
//
//        // login
//        Seller loginSeller = service.login(singupSeller.getLoginId(), singupSeller.getPassword());
//        assertThat(loginSeller).isEqualTo(singupSeller);
//
//        // find seller
//        Seller findSeller2 = service.findByLoginId(singupSeller.getLoginId());
//        assertThat(findSeller2).isEqualTo(singupSeller);
//
//        // delete
//        service.delete(singupSeller.getId());
//        assertThatThrownBy(() -> service.findByLoginId(singupSeller.getLoginId())).isInstanceOf(NoSuchElementException.class);
    }

}