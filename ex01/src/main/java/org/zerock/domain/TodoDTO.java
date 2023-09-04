package org.zerock.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TodoDTO {

    private String title;

    /**
     * DateTimeFormat을 사용하면 InitBinder로 처리하지 않아도 변환이 가능하다.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
}
