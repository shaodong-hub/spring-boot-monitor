package com.github.springbootmonitor.controller.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springbootmonitor.pojo.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>
 * 创建时间为 19:01 2019-06-04
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MongoFileControllerImplTest {


    /**
     * 模拟mvc测试对象
     */
    @Resource
    private MockMvc mockMvc;


    @Test
    public void getAllFileNames() throws Exception {
        String result = mockMvc.perform(get("/file"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResultDO<Set<String>>> typeReference = new TypeReference<ResultDO<Set<String>>>() {
        };
        ResultDO<Set<String>> resultDO = new ObjectMapper().readValue(result, typeReference);
        Assert.assertEquals(0, resultDO.getStatus().intValue());
        Assert.assertEquals(resultDO.getData().size(), 0);

    }

    @Test
    public void upload() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/shao/Documents/IdeaProjects/spring-boot-monitor/src/test/resources/aaaa.txt"));
        String result = mockMvc.perform(multipart("/file/upload")
                .file(new MockMultipartFile("file", "aaaa.txt", MediaType.MULTIPART_FORM_DATA_VALUE, bytes)))
//                .file(new MockMultipartFile("file", "test123.txt", MediaType.MULTIPART_FORM_DATA_VALUE, "hello upload".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);


    }

    @Test
    public void download() throws Exception {

    }

    @Test
    public void delete() throws Exception {
    }
}