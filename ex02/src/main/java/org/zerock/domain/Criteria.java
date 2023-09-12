package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    // 페이지 번호
    private int pageNum;

    // 페이지당 보여질 수
    private int amount;

    // 기본 1페이지 10개의 게시물
    public Criteria() {
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
