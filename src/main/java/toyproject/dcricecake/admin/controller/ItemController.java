package toyproject.dcricecake.admin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;
import toyproject.dcricecake.admin.domain.seller.Seller;
import toyproject.dcricecake.admin.domain.seller.login.SellerSessionConst;
import toyproject.dcricecake.admin.file.FileStore;
import toyproject.dcricecake.admin.service.ItemService;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final FileStore fileStore;

    //메인 페이지
    @GetMapping
    public String home(Model model, @SessionAttribute(name = SellerSessionConst.LOGIN_MEMBER, required = false) Seller seller) {
        if (seller == null) {
            return "admin/home";
        }
        model.addAttribute("loginId", seller.getLoginId());

        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "admin/home";
    }

    // 상품 추가페이지
    @GetMapping("/items/add")
    public String addForm(@ModelAttribute("item") Item item) {
        return "admin/item/add";
    }

    @PostMapping("/items/add")
    public String add(@Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "/admin/item/add";
        }

        log.info("ItemUpdateForm = {}", form);
        Long itemId = itemService.add(form);
        log.info("Save item = {}", itemId);
        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/admin/items/{itemId}";
    }

    // 상품 상세페이지
    @GetMapping("/items/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "admin/item/item";
    }

    // 상품 수정페이지
    @GetMapping("/items/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "admin/item/edit";
    }

    @PostMapping("/items/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "admin/item/edit";
        }

        itemService.update(itemId, form);
        return "redirect:/admin/items/{itemId}";
    }

    // 이미지
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource image(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
