package toyproject.dcricecake.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.repository.item.ItemRepository;
import toyproject.dcricecake.customer.domain.Customer;
import toyproject.dcricecake.customer.domain.login.CustomerSessionConst;
import toyproject.dcricecake.customer.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CustomerPageController {

    private final ItemRepository repository;
    private final CustomerService service;

    // 메인
    @GetMapping
    public String home(Model model, @SessionAttribute(name = CustomerSessionConst.LOGIN_MEMBER, required = false)Customer customer) {
        if (customer == null) {
            List<Item> items = repository.findAll();
            model.addAttribute("items", items);

            return "/customer/home";
        }
        model.addAttribute("loginId", customer.getLoginId());

        List<Item> items = repository.findAll();
        model.addAttribute("items", items);

        return "customer/home";
    }

    // 상품 상세페이지 (구매페이지 겸)
    @GetMapping("/items/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = repository.findById(itemId);
        model.addAttribute("item", findItem);

        return "customer/item";
    }

    // 구매
    @PostMapping("/items/{itemId}")
    public String buyItem(@PathVariable Long itemId, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Integer buyQuantity = (Integer) Integer.parseInt(request.getParameter("buyQuantity"));
        Item findItem = repository.findById(itemId);

        service.buy(findItem, buyQuantity);

        String buyId = UUID.randomUUID().toString();

        redirectAttributes.addAttribute("buyQuantity", buyQuantity);

        return "redirect:/items/{itemId}/" + buyId;
    }

    // 구매완료 페이지(PRG)
    @GetMapping("/items/{itemId}/{buyId}")
    public String checkBuy(@PathVariable Long itemId, Model model) {
        Item findItem = repository.findById(itemId);
        model.addAttribute("item", findItem);

        return "customer/checkBuy";
    }
}
