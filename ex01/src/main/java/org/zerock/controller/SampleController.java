package org.zerock.controller;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleDTO;
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

    /**
     * Java Beans 규칙에 맞는 객체(생성자가 없거나 빈 생성자가 있을 때)는 다시 화면으로 전달된다 (DTO의 경우)
     * 기본자료형의 경우에는 화면까지 전달되지 않기 때문에 @ModelAttribute를 사용하면 화면까지 전달이 된다.
     * @param dto
     * @param page
     * @return
     */
    @RequestMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        System.out.println("dtd : " + dto);
        System.out.println("page : " + page);
        return "/sample/ex04";
    }

    /**
     * jackson-databind를 사용하면 DTO를 return하면 json으로 변환되어 리턴된다.
     * (Spring Boot에선 기본적으로 지원하기 때문에 의존성은 필요없다.)
     * @return
     */
    @RequestMapping("/ex06")
    @ResponseBody
    public SampleDTO ex06() {
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;
    }

    /**
     * ResponseEntity로 원하는 헤더 정보나 데이터를 전달할 수 있다.
     * RestAPI에서 쓰면 유용
     * @return
     */
    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        String msg = "{\"name\": \"홍길동\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        /*return new ResponseEntity<>(msg, headers, HttpStatus.OK);*/
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(msg);
    }
}
