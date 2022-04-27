package toyproject.dcricecake.admin.repository.item;

import org.junit.jupiter.api.Test;
import toyproject.dcricecake.admin.domain.item.Item;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryItemRepositoryTest {

    ItemRepository repository = new MemoryItemRepository();

    @Test
    void saveAndFindById() {
        Item item = new Item();
        item.setItemName("테스트");

        repository.save(item);

        Item findItem = repository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        Item item = new Item();
        Item item2 = new Item();

        item.setItemName("테스트1");
        item2.setItemName("테스트2");

        repository.save(item);
        repository.save(item2);

        List<Item> items = repository.findAll();
        assertThat(items.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        Item item = new Item();
        item.setItemName("테스트");

        repository.save(item);

        repository.delete(item.getId());

        Item findItem = repository.findById(item.getId());
        assertThat(findItem).isNull();
    }
}