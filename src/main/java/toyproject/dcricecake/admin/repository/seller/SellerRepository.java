package toyproject.dcricecake.admin.repository.seller;

import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;

import java.util.List;
import java.util.Optional;

public interface SellerRepository {

    void save(SellerSignupForm form);

    void delete(Long id);

    Optional<Seller> findByLoginId(String loginId);

    List<Seller> findAll();
}
