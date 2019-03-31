package com.honghap.sample.sample;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by gavinkim at 2019-03-30
 */
@Getter
@Setter
@ToString
public class SampleDTO {

    private String message;

    @Builder
    public SampleDTO(String message) {
        this.message = message;
    }
}
