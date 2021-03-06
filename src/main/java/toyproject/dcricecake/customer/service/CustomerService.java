package toyproject.dcricecake.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;
import toyproject.dcricecake.admin.repository.item.ItemRepository;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.CustomerSignupForm;
import toyproject.dcricecake.customer.repository.CustomerRepository;
import toyproject.dcricecake.exception.MyNotSamePWException;
import toyproject.dcricecake.exception.MySameLoginIdException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;

    // 구매
    public void buy(Item item, Integer quantity) {
        if (item.getQuantity() != null && quantity != null) {
            if (item.getQuantity() < quantity) {
                // 구매수량 오류
            }
        }

        ItemUpdateForm itemUpdateForm = new ItemUpdateForm();
        itemUpdateForm.setItemName(item.getItemName());
        itemUpdateForm.setPrice(item.getPrice());
        itemUpdateForm.setQuantity(item.getQuantity() - quantity);

        itemRepository.update(item.getId(), itemUpdateForm);
    }

    // 로그인
    public Customer login(String loginId, String password) {
        return customerRepository.findByLoginId(loginId)
                .filter(s -> s.getPassword().equals(password))
                .orElse(null);
    }

    // 로그아웃
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    // 회원가입
    public void signup(CustomerSignupForm form) {
        // 비밀번호 다름 오류
        if (!form.getPassword().equals(form.getCheckPassword())) {
            throw new MyNotSamePWException();
        }

        // 중복아이디 오류
        if (customerRepository.findByLoginId(form.getLoginId()).get() != null) {
            throw new MySameLoginIdException();
        }


        customerRepository.save(form);
    }

    // 회원 찾기(아이디로)
    public Customer findByLoginId(String loginId) {
        return customerRepository.findByLoginId(loginId).get();
    }


}