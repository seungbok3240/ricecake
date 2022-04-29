package toyproject.dcricecake.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;
import toyproject.dcricecake.admin.repository.item.ItemRepository;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 상품 추가, 수정, 삭제
 */

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public Item add(ItemUpdateForm form) {
        Item item = new Item();
        itemMapper(item, form);

        return repository.save(item);
    }

    void delete(Long id) {
        repository.delete(id);
    }

    public void update(Long itemId, ItemUpdateForm form) {
        repository.update(itemId, form);
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(Long id) {
        return repository.findById(id);
    }

    private void itemMapper(Item item, ItemUpdateForm form) {
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
    }

}
