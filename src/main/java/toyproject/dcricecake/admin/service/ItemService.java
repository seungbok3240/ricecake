package toyproject.dcricecake.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toyproject.dcricecake.admin.domain.item.Item;
import toyproject.dcricecake.admin.domain.item.ItemUpdateForm;
import toyproject.dcricecake.admin.domain.item.UploadFile;
import toyproject.dcricecake.admin.file.FileStore;
import toyproject.dcricecake.admin.repository.item.ItemRepository;

import java.io.IOException;
import java.util.List;

/**
 * 상품 추가, 수정, 삭제
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final FileStore fileStore;

    public Long add(ItemUpdateForm form) {
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
        try {
            UploadFile image = fileStore.storeFile(form.getImage());
            List<UploadFile> detailImages = fileStore.storeFiles(form.getDetailImages());

            item.setItemName(form.getItemName());
            item.setPrice(form.getPrice());
            item.setQuantity(form.getQuantity());
            item.setImage(image);
            item.setDetailImages(detailImages);
        } catch (IOException e) {
            // 예외를 계속 올리기 싫어서 여기서 잡았다. 어떻게 처리하는지를 몰라서 코드의 변경을 최소화하고 싶었다. 22.05.06
            e.printStackTrace();
        }
    }

}
