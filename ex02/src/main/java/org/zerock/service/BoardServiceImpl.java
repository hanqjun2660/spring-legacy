package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

//    @Setter(onMethod_ = @Autowired) Lombok Setter를 이용한 자동주입
    private BoardMapper mapper;

    /**
     * 왠만하면 생성자를 이용한 자동주입을 사용하자.
     * @param mapper
     */
    @Autowired
    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void register(BoardVO board) {

    }

    @Override
    public BoardVO get(Long bno) {
        return null;
    }

    @Override
    public boolean modify(BoardVO board) {
        return false;
    }

    @Override
    public boolean remove(Long bno) {
        return false;
    }

    @Override
    public List<BoardVO> getList() {
        return null;
    }
}
