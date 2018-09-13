package com.ga.cdz.domain.vo.api;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EnumDemoVo {

    private MyState myState;


    public enum MyState implements IEnum<Integer> {
        ONE(-1, "one"),
        TWO(9, "two"),
        THREE(100, "three");

        private Integer code;
        private String desc;

        MyState(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.code;
        }

        @JsonValue
        public String getDesc() {
            return desc;
        }
    }
}
