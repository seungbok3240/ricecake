package toyproject.dcricecake.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.SellerSignupForm;
import toyproject.dcricecake.admin.login.SellerLoginForm;
import toyproject.dcricecake.admin.login.SellerSessionConst;
import toyproject.dcricecake.admin.service.SellerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    // 회원가입 페이지
    @GetMapping("/new")
    public String signupForm(@ModelAttribute("form") SellerSignupForm form) {
        return "admin/seller/new";
    }

    @PostMapping("/new")
    public String singup(@ModelAttribute SellerSignupForm form) {
        sellerService.singup(form);
        return "redirect:/admin";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") SellerLoginForm form) {
        return "admin/seller/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute SellerLoginForm form, HttpServletRequest request) {
        Seller seller = sellerService.login(form.getLoginId(), form.getPassword());

        // 로그인 실패
        if (seller == null) {

        }

        // 로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute(SellerSessionConst.LOGIN_MEMBER, seller);

        return "redirect:/admin";
    }


    // 회원 찾기(나중에, 22.04.28)

    // 회원 탈퇴(나중에, 22.04.28)
}
