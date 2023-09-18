package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.ReplyVO;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {

    private Long[] bnoArr = { 233L, 141L, 127L, 126L, 125L, 124L, 123L, 122L, 81L };

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void testMapper() {
        log.info(replyMapper);
    }

    @Test
    public void testCreate() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO replyVO = new ReplyVO();

            replyVO.setBno(bnoArr[i % 9]);
            replyVO.setReply("테스트");
            replyVO.setReplyer("replayer" + i);

            replyMapper.insert(replyVO);
        });
    }

    @Test
    public void testRead() {
        Long targetRno = 1L;

        ReplyVO replyVO = replyMapper.read(targetRno);

        log.info(replyVO);
    }

    @Test
    public void testDelete() {
        Long targetRno = 1L;
        log.info(replyMapper.delete(targetRno));
    }

    @Test
    public void testUpdate() {
        Long targetRno = 2L;

        ReplyVO replyVO = new ReplyVO();
        replyVO.setReply("업데이트된 내용");
        replyVO.setRno(targetRno);
        replyVO.setReplyer("업데이트된 작성자");

        log.info(replyMapper.update(replyVO));
    }
}
