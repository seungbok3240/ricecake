package toyproject.dcricecake.admin.domain.seller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Seller {


    private Long id;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
