package com.example.redis.enumtest;

import com.google.common.collect.Lists;
import lombok.Getter;
import com.example.redis.model.DictDTO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统识别故障枚举值 0=无 1=线损
 *
 * @author liuziyu
 * @date 2021/05/07
 */
@Getter
public enum SystemIdentifyFailEnum {
    UNKNOWN(-1, "未知"),
    NO(0, "无"),
    LINE_LOSS(1, "线损"),
    WIRE_DAMAGE(2, "充电线线断"),
    SHELL_DAMAGE(4, "外壳破损"),
    CANNOT_FULL(8, "充电宝时间短"),
    CANNOT_CHARGE(16,"无法充电");

    private Integer value;

    private String desc;

    SystemIdentifyFailEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static SystemIdentifyFailEnum of(Integer key) {
        return Arrays.stream(SystemIdentifyFailEnum.values())
                .filter(c -> c.getValue().equals(key))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public static List<DictDTO> getList() {
        List<SystemIdentifyFailEnum> systemIdentifyFailEnums = Lists.newArrayList(
                SystemIdentifyFailEnum.NO,
                SystemIdentifyFailEnum.LINE_LOSS,
                SystemIdentifyFailEnum.WIRE_DAMAGE,
                SystemIdentifyFailEnum.SHELL_DAMAGE,
                SystemIdentifyFailEnum.CANNOT_FULL,
                SystemIdentifyFailEnum.CANNOT_CHARGE
        );
        return systemIdentifyFailEnums.stream().map(c -> {
            final DictDTO dictDTO = new DictDTO();
            dictDTO.setKey(c.getValue());
            dictDTO.setValue(c.getDesc());
            return dictDTO;
        }).collect(Collectors.toList());
    }

}

