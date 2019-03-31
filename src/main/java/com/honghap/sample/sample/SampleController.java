package com.honghap.sample.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gavinkim at 2019-03-29
 */
@RestController
public class SampleController {

    private static final String WELCOME_MESSAGE = "welcome, %s!";

    @GetMapping("/sample")
    public String sample(){
        return "welcome";
    }

    @GetMapping("/sample/dto")
    public SampleDTO getSampleDto(){
        return SampleDTO.builder().message("welcome").build();
    }


    @GetMapping("/sample-with-parameter/{name}")
    public SampleDTO sampleWithParameter(@PathVariable String name) {
        return SampleDTO.builder().message(String.format(WELCOME_MESSAGE,name)).build();
    }
}
