package toyproject.dcricecake.admin.repository.item;

import org.springframework.stereotype.Repository;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// @Repository
public class MemoryItemRepository implements ItemRepository{

    private static Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Long save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item.getId();
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void update(Long itemId, ItemUpdateForm form) {
        Item findItem = findById(itemId);

        findItem.setItemName(form.getItemName());
        findItem.setPrice(form.getPrice());
        findItem.setQuantity(form.getQuantity());
    }

    // 테스트 케이스용
    public void clearAll() {
        store.clear();
    }
}
