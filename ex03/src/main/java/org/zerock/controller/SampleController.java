package org.zerock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    /*
        HTTP의 전송방식을 알아두자.
        REST 방식의 데이터 교환에서는 GET/POST 방식 외의 다양한 방식으로 데이터를 전달한다.
        CREATE -> POST(HTTP)
        READ - > GET(HTTP)
        UPDATE -> PUT(HTTP)
        DELETE - > DELETE(HTTP)
     */

    /**
     * produces : 매서드가 생산하는 MIME 타입을 의미한다.
     * MediaType클래스를 이용하여 MIME 타입을 지정할수도 있다.
     * @return
     */
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText() {
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        return "안녕하세요";
    }

    @GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample() {
        return new SampleVO(112, "스타", "로드");
    }

    /**
     * produces는 생략이 가능하다. (위와 동일하게 xml, json형태로 받을 수 있다.)
     * @return
     */
    @GetMapping("/getSample2")
    public SampleVO getSample2() {
        return new SampleVO(112, "스타", "로드");
    }

    // 컬렉션 타입으로 return해보자
    @GetMapping("/getList")
    public List<SampleVO> getList() {
        // 1 부터 10미만까지 루프를 돌면서 SampleVO 객체를 만들어 List로 만든다.
        return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last"))
                .collect(Collectors.toList());
    }

    // Map형태로 return 해보자
    @GetMapping("/getMap")
    public Map<String, SampleVO> getMap() {
        Map<String, SampleVO> map = new HashMap<>();
        map.put("First", new SampleVO(111, "그루트", "주니어"));
        return map;
    }

    // ResponseEntity 형태로 return을 해보자
    // ResponseEntity는 데이터와 함께 헤더에 상태메세지와 코드등을 같이 전달해 통신이 정상적으로 이루어졌는지 확인할 수 있다.
    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight) {

        SampleVO vo = new SampleVO(0, "" + height, "" + weight);

        ResponseEntity<SampleVO> result = null;

        if(height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }

        return result;
    }

    /*
        @RestController에서의 파라미터
            @PathVariable : URL 경로의 일부를 파라미터로 사용할 때
            @RequestBody : JSON 데이터를 원하는 타입의 객체로 변환해야하는 경우
     */

    // @PathVariable을 사용하여 파라미터를 받아 사용해보자
    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
        return new String[] {"category: " + cat, "productid: " + pid};
    }

    // @RequestBody를 사용해보자
    // 요청 헤더에 application/type이 json으로 되어 있어야 한다.(테스트 코드 필요없이 postman 같은곳에서 요청하면 확인됨)
    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket) {
        log.info("convert.......ticket" + ticket);
        return ticket;
    }
}
