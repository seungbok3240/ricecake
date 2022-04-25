package toyproject.dcricecake.admin.repository.item;

import org.springframework.stereotype.Repository;
import toyproject.dcricecake.admin.domain.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 나중에 H2 DB로 바꿀 예정
 */
@Repository
public class MemoryItemRepository implements ItemRepository{

    private final Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
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
}
