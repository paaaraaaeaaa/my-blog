package com.wanted.chap02handlermethod;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MenuDTO {

    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

}
