package toyproject.dcricecake.admin.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateForm {

    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000)
    private Integer price;

    @NotNull
    @Range(min = 1, max = 9999)
    private Integer quantity;

    // 이미지 추가하기 22.04.25
}
