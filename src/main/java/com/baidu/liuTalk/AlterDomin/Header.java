package com.baidu.liuTalk.AlterDomin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@JsonInclude
@Data
public class Header {
    private int toid;

    public String toString() {
        return "\"header\":{\"toid\":["+this.toid+"]}";
    }
}
