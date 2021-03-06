package com.honghap.sample.sample;

import com.honghap.sample.sample.SampleController;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by gavinkim at 2019-03-29
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerUnitTest {

    @Autowired private MockMvc mvc;

    @Test
    public void sample() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/sample")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("welcome")));
    }

    @Test
    public void getSampleDto() throws Exception{
        mvc.perform(
                MockMvcRequestBuilders.get("/sample/dto")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("welcome")));
    }

    @Test
    public void sampleWithParameter() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/sample-with-parameter/gavin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("welcome, gavin!")));
    }
}
