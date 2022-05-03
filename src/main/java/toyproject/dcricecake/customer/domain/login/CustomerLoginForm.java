package toyproject.dcricecake.customer.domain.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerLoginForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
