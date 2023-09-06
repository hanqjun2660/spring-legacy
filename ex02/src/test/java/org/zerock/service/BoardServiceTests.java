package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ObjectUtils;
import org.zerock.domain.BoardVO;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
public class BoardServiceTests {

    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;

    @Test
    public void testExist() {
        log.info(boardService);
        assertNotNull(boardService);
    }

    @Test
    public void testRegister() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성하는 글");
        boardVO.setContent("새로 작성하는 내용");
        boardVO.setWriter("newbie");

        boardService.register(boardVO);

        log.info("생성된 게시물의 번호: " + boardVO.getBno());
    }

    @Test
    public void testGetList() {
        boardService.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testGet() {
        log.info(boardService.get(1L));
    }

    @Test
    public void testUpdate() {
        BoardVO boardVO = boardService.get(1L);

        if(boardVO == null) {
            return;
        }

        boardVO.setTitle("제목을 수정합니다.");
        boardService.modify(boardVO);
    }

    @Test
    public void testDelete() {
        BoardVO boardVO = boardService.get(1L);

        if(ObjectUtils.isEmpty(boardVO)) {
           log.info("존재하지 않는 게시글");
           return;
        }

        log.info("존재하는 게시글 삭제");
        boardService.remove(boardVO.getBno());
    }
}
