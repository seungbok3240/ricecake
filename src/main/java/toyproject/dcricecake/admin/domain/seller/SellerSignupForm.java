package toyproject.dcricecake.admin.domain.seller;

import lombok.Data;

@Data
public class SellerSignupForm {

    private Long id;

    private String loginId;

    private String password;

    private String checkPassword;
}
