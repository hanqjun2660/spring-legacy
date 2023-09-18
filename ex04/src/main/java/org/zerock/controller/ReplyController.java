package org.zerock.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Slf4j
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo) {

        log.info("ReplyVO " + vo);

        int insertCount = replyService.register(vo);

        log.info("Reply INSERT COUNT " + insertCount);

        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/pages/{bno}/{page}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page) {
        log.info("getList...........");
        Criteria cri = new Criteria(page, 10);
        log.info(String.valueOf(cri));

        return new ResponseEntity<>(replyService.getList(cri, bno), HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
        log.info("get........" + rno);
        return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        log.info("remove.........." + rno);
        return replyService.remove(rno) == 1 ? new ResponseEntity<>("sucess", HttpStatus.OK) : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
                    value = "/{rno}",
                    consumes = "application/json",
                    produces = { MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> modify(@PathVariable("rno") Long rno, @RequestBody ReplyVO vo) {
        log.info("modify.............." + rno);

        vo.setRno(rno);

        log.info("rno : " + rno);
        log.info("vo : " + vo);

        return replyService.modify(vo) == 1 ? new ResponseEntity<>("sucess", HttpStatus.OK) : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
