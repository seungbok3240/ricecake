package toyproject.dcricecake.admin.domain.seller.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SellerLoginForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
