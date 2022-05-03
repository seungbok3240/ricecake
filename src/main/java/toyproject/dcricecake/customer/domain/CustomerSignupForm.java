package toyproject.dcricecake.customer.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerSignupForm {

    private Long id;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String checkPassword;
}
