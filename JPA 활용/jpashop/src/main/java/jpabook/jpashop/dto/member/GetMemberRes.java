package jpabook.jpashop.dto.member;

import jpabook.jpashop.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GetMemberRes<T> {

    // private int count; 향후에 카운트에 대한 필드를 자유롭게 추가할 수 있다.
    private T data;

}
