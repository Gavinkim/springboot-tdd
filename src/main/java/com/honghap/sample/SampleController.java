package com.honghap.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gavinkim at 2019-03-29
 */
@RestController
public class SampleController {

    @GetMapping("/sample")
    public String sample(){
        return "welcome";
    }

    @GetMapping("/sample/dto")
    public SampleDTO getSampleDto(){
        return SampleDTO.builder().message("welcome").build();
    }

}
