package toyproject.dcricecake.admin.repository.item;

import toyproject.dcricecake.admin.domain.item.Item;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll();

    void delete(Long id);
}
