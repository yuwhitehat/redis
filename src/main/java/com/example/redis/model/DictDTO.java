package com.example.redis.model;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DictDTO {

    private Integer key;

    private String value;

    public DictDTO(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
