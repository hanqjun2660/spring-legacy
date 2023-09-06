package org.zerock.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Log4j
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class, org.zerock.config.ServletConfig.class})
public class BoardControllerTests {

    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;

    private MockMvc mockMvc;        // 가짜 MVC를 만들어 Controller를 테스트할 수 있다. (Was실행 불필요)

    @Before
    public void setup() {
        // 모든 테스트가 실행되기전 아래 코드가 실행되어 가짜 MVC를 만들어준다.
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception{
        // MockRequestBuilders로 Controller를 브라우저에서 이용하듯 테스트할 수 있다.
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );

    }

}
