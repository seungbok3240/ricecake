package toyproject.dcricecake.admin.domain.item;

import lombok.Data;

@Data
public class Item {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    // 이미지 추가하기 22.04.25
}
