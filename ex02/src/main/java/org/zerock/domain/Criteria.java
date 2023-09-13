package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {
    // 페이지 번호
    private int pageNum;

    // 페이지당 보여질 수
    private int amount;

    // 검색조건
    private String type;

    // 검색어
    private String keyword;

    // 기본 1페이지 10개의 게시물
    public Criteria() {
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[] getTypeArr() {
        return type == null ? new String[] {} : type.split("");
    }

    public String getListLink() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.getAmount())
                .queryParam("type", this.getType())
                .queryParam("keyword", this.getKeyword());

        return builder.toUriString();
    }
}
