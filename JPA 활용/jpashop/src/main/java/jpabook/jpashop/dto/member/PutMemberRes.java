package jpabook.jpashop.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PutMemberRes {
    private Long id;
    private String name;
}
