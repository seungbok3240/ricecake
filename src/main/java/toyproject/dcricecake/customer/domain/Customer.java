package toyproject.dcricecake.customer.domain;

import lombok.Data;

@Data
public class Customer {

    private Long id;

    private String loginId;

    private String password;
}
