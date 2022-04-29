package toyproject.dcricecake.customer.domain;

import lombok.Data;

@Data
public class CustomerSignupForm {

    private Long id;

    private String loginId;

    private String password;

    private String checkPassword;
}
