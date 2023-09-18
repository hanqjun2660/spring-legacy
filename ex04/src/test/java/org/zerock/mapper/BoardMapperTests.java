package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        boardMapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey() {

        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성하는 글 selectKey");
        boardVO.setContent("새로 작성하는 내용 selectKey");
        boardVO.setWriter("newbie");

        boardMapper.insertSelectKey(boardVO);

        log.info(boardVO);
    }

    @Test
    public void testRead() {

        BoardVO board = boardMapper.read(5L);

        log.info(board);
    }

    @Test
    public void testDelete() {
        log.info("delete count: " + boardMapper.delete(3L));
    }

    @Test
    public void testUpdate() {

        BoardVO boardVO = new BoardVO();
        boardVO.setBno(5L);
        boardVO.setTitle("수정된 제목");
        boardVO.setContent("수정된 글");
        boardVO.setWriter("user01");

        int count = boardMapper.update(boardVO);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        cri.setPageNum(3);
        cri.setAmount(10);

        List<BoardVO> list = boardMapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board.getBno()));
    }

    @Test
    public void testSearch() {

        Criteria cri = new Criteria();
        cri.setKeyword("새로");
        cri.setType("TC");

        List<BoardVO> list = boardMapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }
}
