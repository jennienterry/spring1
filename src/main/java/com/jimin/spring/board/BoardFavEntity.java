package com.jimin.spring.board;

import lombok.Data;

@Data //Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode. 를 만들어준다.
public class BoardFavEntity {
    private int iboard;
    private int iuser;
}
