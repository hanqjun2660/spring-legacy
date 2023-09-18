package org.zerock.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import java.util.List;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Override
    public int register(ReplyVO vo) {
        log.info("register............" + vo);
        return replyMapper.insert(vo);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("get..............." + rno);
        return replyMapper.read(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("modify............" + vo);
        return replyMapper.update(vo);
    }

    @Override
    public int remove(Long rno) {
        log.info("remove..........." + rno);
        return replyMapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("getList............." + cri + bno);
        return replyMapper.getListwithPaging(cri, bno);
    }
}
