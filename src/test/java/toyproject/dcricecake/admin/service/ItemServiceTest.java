package toyproject.dcricecake.admin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;
import toyproject.dcricecake.admin.repository.item.ItemRepository;
import toyproject.dcricecake.admin.repository.item.MemoryItemRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    ItemRepository itemRepository = new MemoryItemRepository();
    ItemService itemService = new ItemService(itemRepository);

    @Test
    void add_delete_update() {

        // add
        ItemUpdateForm form = new ItemUpdateForm();
        form.setItemName("테스트");
        form.setPrice(10000);
        form.setQuantity(10);

        Item item = itemService.add(form);
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);

        // update
        ItemUpdateForm updateForm = new ItemUpdateForm();
        updateForm.setItemName("이름변경");
        itemService.update(item.getId(), updateForm);
        Item updateFindItem = itemRepository.findById(item.getId());
        assertThat(updateFindItem.getItemName()).isEqualTo("이름변경");

        // delete
        itemService.delete(item.getId());
        assertThat(itemRepository.findById(item.getId())).isNull();
    }
}