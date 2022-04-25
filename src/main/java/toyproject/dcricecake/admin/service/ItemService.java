package toyproject.dcricecake.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;
import toyproject.dcricecake.admin.repository.item.ItemRepository;

/**
 * 상품 추가, 수정, 삭제
 */

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    Item add(ItemUpdateForm form) {
        Item item = new Item();
        itemMapper(item, form);

        return repository.save(item);
    }

    void delete(Long id) {
        repository.delete(id);
    }

    void update(Long itemId, ItemUpdateForm form) {
        Item findItem = repository.findById(itemId);
        itemMapper(findItem, form);
    }

    private void itemMapper(Item item, ItemUpdateForm form) {
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
    }

}
