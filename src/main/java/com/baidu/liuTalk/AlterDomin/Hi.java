package com.baidu.liuTalk.AlterDomin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude
@Data
public class Hi {
    private Header header;
    private Body body ;

    public String toString() {
        return "{\"message\":{"+this.header+","+this.body+"}}";
    }

}
