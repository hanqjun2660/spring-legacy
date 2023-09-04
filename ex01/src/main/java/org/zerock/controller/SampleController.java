package org.zerock.controller;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.TodoDTO;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

    /**
     * String으로 들어온 날짜와 같은 경우 파라미터를 간단하게 Date형으로 변환할 수 있다.
     * 아래와 같이 InitBinder 어노테이션을 사용한다.
     * @param binder
     */
    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }*/

    /**
     * http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
     * @param todoDTO
     * @return
     */
    @RequestMapping("/ex03")
    public String ex03(TodoDTO todoDTO) {
        /*System.out.println("binder로 잘 변환이 되었을까 : " + todoDTO);*/
        System.out.println("DateTimeFormat로 잘 변환이 되었을까 : " + todoDTO);
        return "ex03";
    }
}
