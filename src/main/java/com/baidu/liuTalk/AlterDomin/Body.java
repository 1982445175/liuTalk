package com.baidu.liuTalk.AlterDomin;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Body {
    private String type;

    private String content;

    public String toString() {
        return " \"body\":[{\"type\": \""+this.type+"\", \"content\": \""+this.content+"\"}]";
    }
}
