package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void testMapper() {
        log.info(replyMapper);
    }
}
