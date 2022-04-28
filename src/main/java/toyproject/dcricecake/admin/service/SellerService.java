package toyproject.dcricecake.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;
import toyproject.dcricecake.admin.repository.seller.SellerRepository;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository repository;

    // 회원가입
    public Seller singup(SellerSignupForm form) {
        // 중복 아이디 체크 구현 필요(22.04.28)


        // 회원가입
        return repository.save(form);
    }

    // 회원 찾기(아이디로)
    public Seller findByLoginId(String loginId) {
        return repository.findByLoginId(loginId).get();
    }

    // 회원 탈퇴
    public void delete(Long id) {
        repository.delete(id);
    }
}
