package com.example.redis.model;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @packageName: com.enmonster.platform.ems.dto
 * @description:
 * @author: ChenChu
 * @date: 2020-12-21 17:37:16
 * @version: V1.0.0
 */
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
