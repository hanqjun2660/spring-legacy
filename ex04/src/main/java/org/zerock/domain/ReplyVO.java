package org.zerock.domain;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class ReplyVO {

    private Long rno;
    private Long bno;

    private String reply;
    private String replyer;
    private Date replyDate;
    private Date updateDate;
}
