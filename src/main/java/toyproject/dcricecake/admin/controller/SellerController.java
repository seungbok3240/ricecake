package toyproject.dcricecake.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;
import toyproject.dcricecake.admin.domain.seller.login.SellerLoginForm;
import toyproject.dcricecake.admin.domain.seller.login.SellerSessionConst;
import toyproject.dcricecake.admin.service.SellerService;
import toyproject.dcricecake.exception.MyNotSamePWException;
import toyproject.dcricecake.exception.MySameLoginIdException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    // 회원가입 페이지
    @GetMapping("/new")
    public String signupForm(@ModelAttribute("form") SellerSignupForm form) {
        return "admin/seller/new";
    }

    @PostMapping("/new")
    public String singup(@Validated @ModelAttribute("form") SellerSignupForm form, BindingResult bindingResult, HttpServletRequest request) {

        try {
            sellerService.signup(form);
        } catch (MyNotSamePWException e) {
            log.error("Not Same PW / check PW", e);
            bindingResult.rejectValue("checkPassword", "DiffPW", "비밀번호가 일치하지 않습니다.");
        } catch (MySameLoginIdException e) {
            log.error("Same LoginId Exception", e);
            bindingResult.reject("SameID", "이미 사용중인 아이디입니다.");
        } finally {
            if (bindingResult.hasErrors()) {
                return "admin/seller/new";
            }
        }

        Seller seller = sellerService.findByLoginId(form.getLoginId());

        HttpSession session = request.getSession();
        session.setAttribute(SellerSessionConst.LOGIN_MEMBER, seller);

        return "redirect:/admin";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") SellerLoginForm form) {
        return "admin/seller/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("form") SellerLoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        Seller seller = sellerService.login(form.getLoginId(), form.getPassword());

        // 로그인 실패
        if (seller == null) {
            bindingResult.reject("loginFail", "아이디와 비밀번호를 확인해주세요.");
        }

        if (bindingResult.hasErrors()) {
            return "admin/seller/login";
        }

        // 로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute(SellerSessionConst.LOGIN_MEMBER, seller);

        return "redirect:/admin";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/admin";
    }

    // 회원 찾기(나중에, 22.04.28)

    // 회원 탈퇴(나중에, 22.04.28)
}
