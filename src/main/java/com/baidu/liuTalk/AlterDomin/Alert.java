package com.baidu.liuTalk.AlterDomin;


import lombok.Data;

@Data
public class Alert {

    private String status;
    private Labels labels;
    private Annotations annotations;
    private String startsAt;
    private String endsAt;
    private String generatorURL;

}
