package toyproject.dcricecake.admin.repository.item;

import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll();

    void delete(Long id);

    void update(Long itemId, ItemUpdateForm form);
}
