package toyproject.dcricecake.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.CustomerSignupForm;
import toyproject.dcricecake.customer.domain.login.CustomerSessionConst;
import toyproject.dcricecake.customer.repository.CustomerRepository;
import toyproject.dcricecake.customer.service.CustomerService;
import toyproject.dcricecake.exception.MyNotSamePWException;
import toyproject.dcricecake.exception.MySameLoginIdException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 회원가입
    @GetMapping("/new")
    public String signupForm(@ModelAttribute("form")CustomerSignupForm form) {
        return "customer/new";
    }

    @PostMapping("/new")
    public String signup(@Validated @ModelAttribute("form") CustomerSignupForm form, BindingResult bindingResult, HttpServletRequest request) {

        try {
            customerService.signup(form);
        } catch (MyNotSamePWException e) {
            log.error("Not Same PW / check PW", e);
            bindingResult.rejectValue("checkPassword", "DiffPW", "비밀번호가 일치하지 않습니다.");
        } catch (MySameLoginIdException e) {
            log.error("Same LoginId Exception", e);
            bindingResult.reject("SameID", "이미 사용중인 아이디입니다.");
        } finally {
            if (bindingResult.hasErrors()) {
                return "customer/new";
            }
        }

        Customer customer = customerService.findByLoginId(form.getLoginId());

        HttpSession session = request.getSession();
        session.setAttribute(CustomerSessionConst.LOGIN_MEMBER, customer);

        return "redirect:/";
    }

    // 로그인
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") CustomerSignupForm form) {
        return "customer/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("form") Customer form, BindingResult bindingResult, HttpServletRequest request) {
        Customer customer = customerService.login(form.getLoginId(), form.getPassword());

        if (customer == null) {
            // 로그인 실패
            log.error("Login Fail : Check ID or PW");
            bindingResult.reject("loginFail", "아이디와 비밀번호를 확인해주세요.");
        }

        if (bindingResult.hasErrors()) {
            log.error("Login Binding Error");
            return "customer/login";
        }

        // 로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute(CustomerSessionConst.LOGIN_MEMBER, customer);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        customerService.logout(request);
        return "redirect:/";
    }
}
